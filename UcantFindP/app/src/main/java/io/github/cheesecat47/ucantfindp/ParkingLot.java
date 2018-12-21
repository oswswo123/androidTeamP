package io.github.cheesecat47.ucantfindp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import static io.github.cheesecat47.ucantfindp.R.color.colorOurPurple;

/*
    주차장의 주차된 현황을 보여주는 액티비티입니다.
    주차장 리스트 액티비티에서 ReserveBtn을 누르면 넘어와져요

    TopText - 상단에 주차가능 주차불가 부분이에요
 */

public class ParkingLot extends AppCompatActivity {
    TextView TopText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_lot);

        //loadDB();

        // 상단부분 주차가능 주차불가 부분
        TopText = (TextView)findViewById(R.id.TopText1);
        String str = "■ 주차가능  ■ 주차불가  ■ 내차위치";
        SpannableStringBuilder spb1 = new SpannableStringBuilder(str);
        // SpannableStringBuilder를 이용해서 부분별로 색깔이랑 사이즈를 변경
        spb1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorOurDarkMint)), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spb1.setSpan(new AbsoluteSizeSpan(80), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spb1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorOurGrey)), 8, 9, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spb1.setSpan(new AbsoluteSizeSpan(80), 8, 9, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spb1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorOurMint)), 16, 17, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spb1.setSpan(new AbsoluteSizeSpan(80), 16, 17, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        TopText.append(spb1);

        // 아직 주차버튼 리스너는 없어요... 이걸 어떻게 구현해야 할까요...?
    }

    // public void loadDB() {
    // 아이디의 주차장 이름 불러오는 쿼리 날리기
    // 내 아이디 쿼리의 내차위치 불러오기
    // }
}
