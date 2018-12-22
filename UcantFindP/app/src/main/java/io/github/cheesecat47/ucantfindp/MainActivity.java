package io.github.cheesecat47.ucantfindp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SocketTest1 socketTest1 = new SocketTest1(54545);
    EditText ID, PW;
    ArrayList<MemberInfo> memberInfoArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 로그인, 회원가입 버튼 클릭 처리
    public void onClick_Enter(View view) {
        switch (view.getId()) {
            case R.id.login: // 로그인 버튼
                EditText loginID = (EditText)findViewById(R.id.loginID);
                EditText loginPW = (EditText)findViewById(R.id.loginPW);

//                MemberInfo 객체 생성
//                sendToServer 메소드에서는 loginID, loginPW와 일치하는 memberInfo 테이블의 memberID,memberPW의 모든 정보를 리턴해서 MemberInfo 객체로 반환(일치 정보가 없으면 null 반환)
                String qur = String.format("SELECT * FROM members WHERE memberID= %s AND memberPw= %s;", "\""+loginID.getText().toString()+"\"", "\""+loginPW.getText().toString()+"\"", "\"F\"");
                Log.i("Tag_send", ""+loginID.getText().toString() + "    " + loginPW.getText().toString());
                String returnFromServer = socketTest1.sendToServer("members",qur);
                Log.i("Tag_return", ""+returnFromServer);
                MyParser MemberParser = new MyParser(true, returnFromServer);
                memberInfoArray = MemberParser.getMemberInfoArr();

                if(returnFromServer == null){
                    Toast.makeText(this, "아이디 또는 비밀번호를 확인해주세요.",Toast.LENGTH_SHORT).show();
                }
                else{
                    // 아이디의 주차 여부 검사
                    if(memberInfoArray.get(0).isParkTF().equals("F")){  // 주차 여부 == false이면 주차장 리스트 화면으로 이동
                        Intent logIntent = new Intent(this, ParkListActivity.class);
                        logIntent.putExtra("memberID", loginID.getText().toString());
                        startActivity(logIntent);
                        finish();
                    }
                    else{ // 주차 여부 == true이면 주차장 좌석 화면으로 이동 , memberID 값을 넘겨줘서 ParkingLot 클래스에서 자신의 차량위치 로드
                        Intent logIntent = new Intent(this, ParkingLot.class);
                        logIntent.putExtra("memberID", loginID.getText().toString());
                        startActivity(logIntent);
                        finish();
                    }

                }
                break;
            case R.id.join: // 회원가입 버튼
                LinearLayout dialogView = (LinearLayout) View.inflate(
                        this, R.layout.layout_join_dialog, null);
                ID = (EditText)dialogView.findViewById(R.id.setId);
                PW = (EditText)dialogView.findViewById(R.id.setPw);
                new AlertDialog.Builder(this)
                        .setTitle("회원 가입")
                        .setView(dialogView)
                        .setPositiveButton("완료", mClick)
                        .setNegativeButton("취소", mClick)
                        .setCancelable(false)
                        .show();
                break;
        }

    }

    // 다이얼로그 리스너
    DialogInterface.OnClickListener mClick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE: // 완료
                    // 회원정보 테이블에 insert 쿼리 날리기
                    String qur = String.format("INSERT INTO members (memberID, memberPw, parkTF) VALUES (%s, %s, %s);", "\""+ID.getText().toString()+"\"", "\""+PW.getText().toString()+"\"", "\"F\"");
                    Log.i("Tag_send", ""+ID.getText().toString() + "\t" + PW.getText().toString());
                    String returnFromServer = socketTest1.sendToServer("members",qur);
                    Log.i("Tag_return", ""+returnFromServer);
                    Toast.makeText(MainActivity.this, "가입되었습니다.", Toast.LENGTH_SHORT).show();
                    break;
                case DialogInterface.BUTTON_NEGATIVE: // 취소
                    Toast.makeText(MainActivity.this, "취소", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}