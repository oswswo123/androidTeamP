package io.github.cheesecat47.ucantfindp;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/*
    ParkListActivity의 ListView에 보일 한 칸에 관련된 내용.
    TextView ParkName - 주차장 이름 보여줄 TextView
    TextView ParkCnt - 주차장 자리 수 보여줄 TextView
    ProgressBar progressBar - 주차장 자리 수 보여줄 가로 progressbar
 */

public class ParkListItem extends LinearLayout {
    private TextView ParkName;
    private TextView ParkCnt;
    private ProgressBar progressBar;
    private Button ReserveBtn;

    Context context;

    public ParkListItem(Context context, ParkInfo data) {
        super(context);
        this.context = context;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //커스텀 뷰를 쓸 땐 인플레이터가 필요합니다.
        inflater.inflate(R.layout.layout_park_list_item, this, true);
        //이 레이아웃 사용한다는 의미.

        ParkName = (TextView) findViewById(R.id.list_item_textView_pName);
        ParkCnt = (TextView) findViewById(R.id.list_item_textView_pCnt);
        progressBar = (ProgressBar) findViewById(R.id.list_item_ProgressBar);
        ReserveBtn = (Button) findViewById(R.id.list_item_ReserveBtn);          //버튼 등록은 여기서 하지만 핸들링은 ParkListActivity에서 합니다.

        ParkName.setText(data.parkName);
        ParkCnt.setText("주차 가능: " + data.parkCntAll + " / 남은 자리: " + data.parkCntLeft);
        progressBar.setMax(data.parkCntAll);                                //전체 자리 수
        progressBar.setProgress((data.parkCntAll - data.parkCntLeft));      //남은 지금 주차된 자리만큼 바 왼쪽에 색깔로 표시.

//        progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#009FB4"), android.graphics.PorterDuff.Mode.SRC_IN);
//        progressBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#009FB4")));
    }
}
