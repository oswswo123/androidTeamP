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
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Runnable {


    //송수신에 필요한 것
    private String hostname = "210.123.39.66";  //서버 IP주소
    private int port = 45454;
    private Socket sock;
    private SocketAddress socketAddress;


    private BufferedWriter sockWriter;
    private BufferedReader sockReader;


    private String toServer = "select * from parkings where isFull=\"N\" and id = 1;";  //보낼 메세지
    private String fromServer = "";     //받은 메세지

    private String flag = "";
    private ArrayList<ParkInfo> ParkInfoArr;   //받은 메세지 파싱 결과(리턴할거)


    //UI 여기는 각 액티비티 상황에 따라 수정. 아래에서 UI 관련 코드 역시 수정.
    private TextView textView;
    private Button button;

    Thread thread = new Thread(this);

    SocketTest1 s;
    String testStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        s = new SocketTest1(45454);
//
//        testStr = s.sendToServer("aa", toServer);
//        Log.i("Tag", "" + testStr);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Thread thread = new Thread();
//                thread.setDaemon(true);
                //thread.start();
//                thread.start();
//                Log.i("Tag", "MainActivity - onClick / start thread");
//
//                try {
//                    Thread.sleep(3000);
//                    Log.i("Tag", "여기1");
//                } catch (InterruptedException e) {
//                    Log.i("Tag", "여기");
//                    e.printStackTrace();
//                }
//
//                thread.interrupt();
//                Log.i("Tag", "MainActivity - onClick / thread interrupted");
                testStr = s.sendToServer("aa", toServer);
            }
        });
    }

    @Override
    public void run() {
        Log.i("Tag", "MainActivity / run Thread");

        try {
            //소켓 생성 & 연결
            sock = new Socket();
            socketAddress = new InetSocketAddress(hostname, port);
            //sock.setSoTimeout(3000);
            //sock.setSoLinger(true, 3000);
            sock.connect(socketAddress);
            Log.i("Tag", sock.toString());


            //소켓에서 쓸 BufferedReader, BufferedWriter
            sockWriter = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
            sockReader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            Log.i("Tag", sockReader.toString());


            sockWriter.write(toServer); //버퍼에 문자열 복사한 후
            sockWriter.flush();         //전송(버퍼 비우고 안에 있는거 다 보냄)
            Log.i("Tag", "MainActivity / send to server" + toServer);


            //그 다음 서버에서 받는 코드
            fromServer = sockReader.readLine();
            Log.i("Tag", "MainActivity / from server: " + android.os.Process.myTid() + fromServer);


            //다 썼으면 정리합니다.
            sockReader.close();
            sockWriter.close();
            sock.close();
            Log.i("Tag", "MainActivity / sock.close");

            parsingParkInfo(fromServer);
            fromServer = "";
            Log.i("Tag", "MainActivity / fromServer(garbage): " + android.os.Process.myTid() + fromServer);

            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Log.i("Tag", "저기");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.i("Tag", "MainActivity / end try-catch");
    }//end run


    //**********************************************************************************************
    //
    //쿼리문을 받아와서 toServer에 저장하고 TCP 통신 스레드 시작합니다.
    //
    //**********************************************************************************************
    public void sendToServer(String flag, String query) {
        toServer = query;
        this.flag = flag;


//        Thread thread = new Thread();
//        thread.setDaemon(true);
//        thread.start();
//        Log.i("Tag", "MainActivity - sentToServer / start thread");
//
//        thread.interrupt();
//        Log.i("Tag", "MainActivity - sendToServer / thread interrupted");

    }


    //**********************************************************************************************
    //
    //쿼리문을 받아와서 toServer에 저장하고 TCP 통신 스레드 시작합니다.
    //
    //**********************************************************************************************
//    class Thread implements Runnable {
//
//        public Thread() {
//            Log.i("Tag", "MainActivity / Thread Constructor");
//        }
//
//
//        @Override
//        public void run() {
//            Log.i("Tag", "MainActivity / run Thread");
//
//            try {
//                //소켓 생성 & 연결
//                sock = new Socket();
//                socketAddress = new InetSocketAddress(hostname, port);
//                //sock.setSoTimeout(3000);
//                //sock.setSoLinger(true, 3000);
//                sock.connect(socketAddress);
//                Log.i("Tag", sock.toString());
//
//
//                //소켓에서 쓸 BufferedReader, BufferedWriter
//                sockWriter = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
//                sockReader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
//                Log.i("Tag", sockReader.toString());
//
//
//                sockWriter.write(toServer); //버퍼에 문자열 복사한 후
//                sockWriter.flush();         //전송(버퍼 비우고 안에 있는거 다 보냄)
//                Log.i("Tag", "MainActivity / send to server" + toServer);
//
//
//                //그 다음 서버에서 받는 코드
//                fromServer = sockReader.readLine();
//                Log.i("Tag", "MainActivity / from server: " + android.os.Process.myTid() + fromServer);
//
//
//                //다 썼으면 정리합니다.
//                sockReader.close();
//                sockWriter.close();
//                sock.close();
//                Log.i("Tag", "MainActivity / sock.close");
//
//                parsingParkInfo(fromServer);
//                fromServer = "";
//                Log.i("Tag", "MainActivity / fromServer(garbage): " + android.os.Process.myTid() + fromServer);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                Log.i("Tag", "MainActivity - finally / Thread end");
//            }
//
//            Log.i("Tag", "MainActivity / end try-catch");
//        }//end run
//    }//end thread


    //**********************************************************************************************
    //
    //fromServer에서 파싱해서 parsedList에 저장하고 이 배열을 반환합니다.
    //
    //**********************************************************************************************
    public ArrayList<ParkInfo> parsingParkInfo(String fromServer) {
        String tempString = fromServer; //혹시 모르니깐 복사해서 사용
        Log.i("Tag", "MainActivity - parsingString / " + tempString);
//(1, 'N', None)(2, 'Y', None)(3, 'Y', None)(4, 'Y', None)(5, 'N', None)(6, 'Y', None)

        return null;
    }
}//end activity
