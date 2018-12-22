package io.github.cheesecat47.ucantfindp;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

/*
    주차장 리스트 액티비티
    로그인 화면 후 이 페이지 보여줍니다. 메인 페이지.

    ListView listView - 주차장 리스트를 보여주는 리스트뷰
    ArrayList<ParkInfo> total_ParkInfo - ParkInfo객체 배열. 주차장 목록 배열입니다. 각 주차장을 이 배열에 넣고 배열을 리스트뷰로 출력합니다.
    ParkListAdapter parkListAdapter - 커스텀 뷰 항목을 리스트뷰와 연결해주는 어댑터입니다.
 */

public class ParkListActivity extends Activity {

    ListView listView;
    ArrayList<ParkInfo> parkInfoArr;
    ArrayList<ParkListInfo> total_ParkListInfo = new ArrayList<ParkListInfo>();
    ParkListAdapter parkListAdapter;
    String memberID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_list);
        Log.i("TAG", "ParkListActivity: start");

        //서버에 쿼리 보내서 주차장 리스트를 받아온다
        SocketTest1 socketTest1 = new SocketTest1(50000);
        String scteststr = socketTest1.sendToServer("parkinglot", "select * from parkings;");
        Log.d("Tag", "" + scteststr);
        MyParser ParkParser = new MyParser(false, scteststr);
        parkInfoArr = ParkParser.getParkInfoArr();

        int MyParkCntAll = parkInfoArr.size();
        int MyParkCntLeft = 6;
        for (int i = 0; i < parkInfoArr.size(); i++) {
            if (parkInfoArr.get(i).getParkTF().equals("Y")) {
                MyParkCntLeft--;
            } else if (parkInfoArr.get(i).getCarID().equals("None")) {
                continue;
            } else if (parkInfoArr.get(i).getCarID().equals(memberID)) {
                continue;
            } else {
                MyParkCntLeft--;
            }
        }

        //실제로 사용할 주차장
        total_ParkListInfo.add(new ParkListInfo("Sin 주차장", MyParkCntAll, MyParkCntLeft));

        //가상 주차장
        total_ParkListInfo.add(new ParkListInfo("Cosin 주차장", 20, 13));
        total_ParkListInfo.add(new ParkListInfo("Tan 주차장", 40, 27));
        Log.i("TAG", "ParkListActivity: add parkinfo activity");

        listView = (ListView) findViewById(R.id.activity_park_list_listView);    //리스트뷰 등록
        parkListAdapter = new ParkListAdapter(this, total_ParkListInfo);   //어댑터 등록. Context - this, ParkListActivity에서 쓸거고 배열 넘겨줍니다.
        listView.setAdapter(parkListAdapter);                                   //어댑터 연결
        Log.i("TAG", "ParkListActivity: set listview adapter");

        Intent logIntent = getIntent();
        memberID = logIntent.getExtras().getString("memberID");
    }

    @Override
    protected  void onResume(){
        super.onResume();

        SocketTest1 socketTest1 = new SocketTest1(50000);
        String scteststr = socketTest1.sendToServer("parkinglot", "select * from parkings;");
        Log.d("Tag", "" + scteststr);
        MyParser ParkParser = new MyParser(false, scteststr);
        parkInfoArr = ParkParser.getParkInfoArr();

        int MyParkCntAll = parkInfoArr.size();
        int MyParkCntLeft = 6;
        for (int i = 0; i < parkInfoArr.size(); i++) {
            if (parkInfoArr.get(i).getParkTF().equals("Y")) {
                MyParkCntLeft--;
            } else if (parkInfoArr.get(i).getCarID().equals("None")) {
                continue;
            } else if (parkInfoArr.get(i).getCarID().equals(memberID)) {
                continue;
            } else {
                MyParkCntLeft--;
            }
        }

        total_ParkListInfo.get(0).setParkCntAll(MyParkCntAll);
        total_ParkListInfo.get(0).setParkCntLeft(MyParkCntLeft);
    }

    public void onClick_ReserveBtn(View view) {
        //ReserveBtn을 눌렀을 때 할 onClickListener
        Toast.makeText(ParkListActivity.this, "onClick_ReserveBtn", Toast.LENGTH_SHORT).show();
        Log.i("TAG", "ParkListActivity: onClick_ReserveBtn");
        Intent GoParkingLot = new Intent(this, ParkingLot.class);
        GoParkingLot.putExtra("memberID", memberID);
        startActivity(GoParkingLot);
    }
}