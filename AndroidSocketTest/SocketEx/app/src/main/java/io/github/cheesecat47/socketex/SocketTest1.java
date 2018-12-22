package io.github.cheesecat47.socketex;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SocketTest1 extends Activity {

    //송수신에 필요한 것
    private String hostname = "210.123.39.66";  //서버 IP주소
    private int port = 45454;

    BufferedWriter sockWriter;
    BufferedReader sockReader;

    private String toServer = "select * from parkings;";  //보낼 메세지
    private String fromServer = "";     //받은 메세지


    private String flag = "";


    public SocketTest1() {
    }

    public SocketTest1(int port) {
        this.port = port;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClientThread thread = new ClientThread();
                thread.start();
            }
        });
    }

    //**********************************************************************************************
    //
    //쿼리문을 받아와서 toServer에 저장하고 TCP 통신 스레드 시작합니다.
    //
    //**********************************************************************************************
    public String sendToServer(String flag, String query) {
        toServer = query;
        this.flag = flag;

        ClientThread thread = new ClientThread();
        thread.start();

//        try {
//            Log.i("Tag", "여기");
//            thread.join();
//            Log.i("Tag", "저기");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return getFromServer();
    }


    public String getFromServer() {

        return fromServer;
    }

    class ClientThread extends Thread {

        public void run() {

            try {
                Socket socket = new Socket(hostname, port);

                sockWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                sockWriter.write(toServer);
                sockWriter.flush();
                Log.d("ClientThread", "서버로 보냄.1");

//                sockReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                fromServer = sockReader.readLine();

                InputStreamReader is = new InputStreamReader(socket.getInputStream());
                fromServer = is;

//                while ((fromServer = sockReader.readLine())!=null){
//                    Log.d("Tag", "while "+fromServer);
//                }
                Log.d("ClientThread", "받은 데이터1 : " + fromServer);

                fromServer = "";
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


