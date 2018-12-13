package io.github.cheesecat47.ucantfindp;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

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

        listView = (ListView)findViewById(R.id.activity_park_list_listView);
        parkListAdapter = new ParkListAdapter(this, total_ParkInfo);
        listView.setAdapter(parkListAdapter);
    }
}
