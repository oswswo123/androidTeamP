package io.github.cheesecat47.ucantfindp;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static io.github.cheesecat47.ucantfindp.R.color.colorOurPurple;

/*
    주차장의 주차된 현황을 보여주는 액티비티입니다.
    주차장 리스트 액티비티에서 ReserveBtn을 누르면 넘어와져요

    TopText - 상단에 주차가능 주차불가 부분이에요
 */

public class ParkingLot extends Activity implements Button.OnClickListener {
    TextView TopText;
//    String memberID;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_lot);


        // 상단부분 주차가능 주차불가 부분
        TopText = (TextView) findViewById(R.id.TopText1);
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


        //소켓 테스트
        SocketTest1 socketTest1 = new SocketTest1();
        String scteststr = socketTest1.sendToServer("parkinglot", "select * from parkings;");
        Log.d("Tag",""+scteststr);

//        MainActivity로부터 memberID값 받아오기
//        Intent logIntent = getIntent();
//        mamberID = logIntent.getExtras().getString("memberID");

//        주차장 정보 불러오는 쿼리 날리기
//        ParkInfo[] parkinfo = sendToServer("parkings", "SELECT * FROM parkings");


        /*
            DB에서 주차 상태를 받아서 주차공간상의 상태를 바꿔주는 부분입니다.
            DB를 구현하고 나면 되는지 시험해야 할 부분이에요


            for(int i=0; i<6; i++){
                int DbBtnId = getResources().getIdentifier("button" + i+1, "id", getPackageName());
                button = (Button)findViewById(DbBtnId);

                if(parkinfo[i].parkTR == "N") { //주차 공간이 비어있는 경우
                    if(parkinfo[i].carID == null) { // 주차 공간에 등록된 아이디가 없을 경우
                        button.setBackgroundResource(R.drawable.drawable_parkinglot_withseat);
                    }
                    else{ // 주차 공간에 등록된 아이디가 있을 경우(예약자가 있는 경우)
                        button.setBackgroundResource(R.drawable.drawable_parkinglot_noseat);
                        혹은
                        button.setBackgroundDrawable(ContextCompat.getDrawable(Context, R.drawable.Drawable파일));
                    }

                }
                else if(parkinfo[i].parkTR == "Y") { //주차 공간이 차있는 경우
                    if(parkinfo[i].carID == memberID) { // 주차 공간에 등록된 아이디가 membeID와 일치하는 경우
                        button.setBackgroundResource(R.drawable.drawable_parkinglot_myseat);
                    }
                    else{
                        button.setBackgroundResource(R.drawable.drawable_parkinglot_noseat);
                        혹은
                        button.setBackgroundDrawable(ContextCompat.getDrawable(Context, R.drawable.Drawable파일));
                    }
                }
            }
         */
    }

    @Override
    public void onClick(View view) {
        // 주차 공간을 클릭했을 때 일어나는 이벤트
        final Button Click_Button = (Button) findViewById(view.getId());
        final Context mContext = this;
        Drawable.ConstantState View_State = view.getBackground().getConstantState();
        Drawable.ConstantState Withseat_State = ContextCompat.getDrawable(mContext, R.drawable.drawable_parkinglot_withseat).getConstantState();
        if (View_State.equals(Withseat_State)) { // 빈 공간을 클릭했을 때
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
            alertDialogBuilder.setTitle("예약");
            alertDialogBuilder
                    .setMessage("예약 하시겠습니까?")
                    .setCancelable(false)
                    .setPositiveButton("예약",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // 예약하기 기능 추가
                                    // 다른 예약한 칸이 있는지 사전검사
                                    Drawable.ConstantState temp1 = ContextCompat.getDrawable(mContext, R.drawable.drawable_parkinglot_myseat).getConstantState();
                                    Drawable.ConstantState temp2;
                                    Button TempButton;
                                    Boolean Overlap = false;
                                    for (int i = 1; i < 7; i++) {
                                        int resID = getResources().getIdentifier("button" + i, "id", getPackageName());
                                        TempButton = (Button) findViewById(resID);
                                        temp2 = TempButton.getBackground().getConstantState();
                                        if (temp1.equals(temp2)) {
                                            Overlap = true;
                                            break;
                                        }
                                    }
                                    if (Overlap) { //예약한 칸이 있다면 종료
                                        Toast.makeText(ParkingLot.this, "이미 예약한 자리가 있습니다.", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    } else { // 예약한 칸이 없다면 예약
                                        Click_Button.setBackgroundResource(R.drawable.drawable_parkinglot_myseat);
                                        Toast.makeText(ParkingLot.this, "예약되었습니다.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            })
                    .setNegativeButton("취소",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // 취소버튼
                                    dialog.cancel();
                                }
                            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        } else { // 빈 공간이 아닐 때
            Toast.makeText(ParkingLot.this, "예약할 수 없습니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
