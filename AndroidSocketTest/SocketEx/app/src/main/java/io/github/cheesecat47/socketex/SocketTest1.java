package io.github.cheesecat47.socketex;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SocketTest1 extends Activity {

    //송수신에 필요한 것
    private String hostname = "210.123.39.66";  //서버 IP주소
    private int port = 45454;

    private String toServer = "select * from parkings;";  //보낼 메세지
    private String fromServer = "";     //받은 메세지


    private String flag = "";

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

        return fromServer;
    }

    class ClientThread extends Thread {

        public void run() {

            try {
                Socket socket = new Socket(hostname, port);

                BufferedWriter sockWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader sockReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                sockWriter.write(toServer);
                sockWriter.flush();
                Log.d("ClientThread", "서버로 보냄.");
                fromServer = (String) sockReader.readLine();
                Log.d("ClientThread", "받은 데이터 : " + fromServer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


