package io.github.cheesecat47.socketex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addr = "210.123.39.66";
                //String addr = "192.168.0.6";

                ConnectThread thread = new ConnectThread(addr);
                thread.start();
                Log.d("MainActivity", "onClick");
            }
        });
    }


    class ConnectThread extends Thread {
        String hostname;

        public ConnectThread(String addr) {
            hostname = addr;
            Log.d("MainActivity", "Thread Constructor");
        }

        @Override
        public void run() {
            super.run();
            Log.d("MainActivity", "run");
            try {
                int port = 45454;
                int timeout = 3000;
                SocketAddress socketAddress = new InetSocketAddress(hostname,port);
                Socket sock = new Socket();
                try{
                    sock.setSoTimeout(timeout);
                    sock.connect(socketAddress,timeout);
                }catch(SocketException e){
                    e.printStackTrace();
                }

                Log.d("MainActivity", sock.toString());

                //ObjectOutputStream outputStream = new ObjectOutputStream(sock.getOutputStream());
                DataOutputStream outputStream = new DataOutputStream(sock.getOutputStream());
                outputStream.write("select * from parkings;".getBytes());
                outputStream.flush();

                //ObjectInputStream inputStream = new ObjectInputStream(sock.getInputStream());
                DataInputStream inputStream = new DataInputStream(sock.getInputStream());
                //String obj = (String) inputStream.readObject();
                byte[] b = new byte[1024];
                for(int i =0 ; i<b.length;i++){
                    b[i] = 0x00;
                }

                int read_i = inputStream.read(b);
                String obj = new String(b, StandardCharsets.UTF_8);


                Log.d("MainActivity", "서버에서 받은 메세지: " + obj);
                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText(obj);

                sock.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
