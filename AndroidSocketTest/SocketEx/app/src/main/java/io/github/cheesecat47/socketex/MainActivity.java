package io.github.cheesecat47.socketex;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;
    Handler mHandler = null;
//    Handler mHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            Log.i("Tag", "handler");
//            Log.i("Tag", msg.toString());
//            textView.setText("from server: " + msg);
//        }
//    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addr = "210.123.39.66";
                //String addr = "192.168.0.6";

                ConnectThread thread = new ConnectThread(addr);
                thread.start();
                Log.i("Tag", "onClick");
            }
        });
    }


    class ConnectThread extends Thread {
        String hostname;

        public ConnectThread(String addr) {
            hostname = addr;
            Log.i("Tag", "Thread Constructor");
        }

        @Override
        public void run() {
            super.run();
            //Looper.prepare();
            //Log.i("Tag", "Looper.prepare");

            Log.i("Tag", "run");
            try {
                int port = 45454;

                Socket sock = new Socket();
                SocketAddress socketAddress = new InetSocketAddress(hostname, port);
                sock.connect(socketAddress, 3000);

                Log.i("Tag", sock.toString());

//                //ObjectOutputStream outputStream = new ObjectOutputStream(sock.getOutputStream());

//                DataOutputStream outputStream = new DataOutputStream(sock.getOutputStream());
//                outputStream.writeUTF("string from client to server");
//                outputStream.flush();
//
//                //ObjectInputStream inputStream = new ObjectInputStream(sock.getInputStream());
//                //String obj = (String) inputStream.readObject();

//                DataInputStream inputStream = new DataInputStream(sock.getInputStream());
//                String obj = (String) inputStream.readUTF();

                BufferedWriter sockWriter = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
                BufferedReader sockReader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                Log.i("Tag", sockReader.toString());




                //query test
                String parkName = "parkings";

                sockWriter.write("select * from "+parkName+";");
                sockWriter.flush();
                Log.i("Tag", "send string to server");

                final String obj = sockReader.readLine();
                Log.i("Tag", "read string from server");

                Log.i("Tag", "MainActivity / from server: " + obj);
//                textView.setText("from server: "+obj);
//                Message msg = mHandler.obtainMessage();
//                mHandler.sendMessage(msg);
//                mHandler = new Handler();
//                mHandler.post(new Runnable() {
//                    @Override
//                    public void run() {     // 여기서 ui 작업
//                      textView.setText("from server: "+obj);
//                    }
//                });

                sockReader.close();
                sockWriter.close();
                sock.close();

                //Looper.loop();
                //Log.i("Tag", "Looper.loop");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
