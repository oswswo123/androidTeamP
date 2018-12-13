package io.github.cheesecat47.ucantfindp;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ParkListItem extends LinearLayout {
    private TextView ParkName;
    private TextView ParkCnt;
    private ProgressBar progressBar;

    public ParkListItem(Context context, ParkInfo data) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_park_list_item, this, true);

        ParkName = (TextView) findViewById(R.id.list_item_textView_pName);
        ParkCnt = (TextView) findViewById(R.id.list_item_textView_pCnt);
        progressBar = (ProgressBar) findViewById(R.id.list_item_ProgressBar);

        ParkName.setText(data.parkName);
        ParkCnt.setText("주차 가능: " + data.parkCntAll + " / 남은 자리: " + data.parkCntLeft);
        progressBar.setMax(data.parkCntAll);
        progressBar.setProgress((data.parkCntAll-data.parkCntLeft));
    }
}
