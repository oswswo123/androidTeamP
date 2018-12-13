package io.github.cheesecat47.ucantfindp;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/*
    주차장 리스트 액티비티
    ListView listView - 주차장 리스트를 보여주는 리스트뷰
    ArrayList<ParkInfo> total_ParkInfo - ParkInfo객체 배열. 주차장 목록 배열입니다. 각 주차장을 이 배열에 넣고 배열을 리스트뷰로 출력합니다.
    ParkListAdapter parkListAdapter - 커스텀 뷰 항목을 리스트뷰와 연결해주는 어댑터입니다.
 */

public class ParkListActivity extends Activity {

    ListView listView;
    ArrayList<ParkInfo> total_ParkInfo = new ArrayList<ParkInfo>();
    ParkListAdapter parkListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_list);

        //테스트용
        total_ParkInfo.add(new ParkInfo("Sin 주차장", 25, 15));
        total_ParkInfo.add(new ParkInfo("Cosin 주차장", 20, 13));
        total_ParkInfo.add(new ParkInfo("Tan 주차장", 40, 27));

        listView = (ListView)findViewById(R.id.activity_park_list_listView);    //리스트뷰 등록
        parkListAdapter = new ParkListAdapter(this, total_ParkInfo);   //어댑터 등록. Context - this, ParkListActivity에서 쓸거고 배열 넘겨줍니다.
        listView.setAdapter(parkListAdapter);                                   //어댑터 연결
    }
}
