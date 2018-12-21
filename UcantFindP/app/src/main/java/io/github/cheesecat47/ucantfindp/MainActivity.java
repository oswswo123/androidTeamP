package io.github.cheesecat47.ucantfindp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 로그인, 회원가입 버튼 클릭 처리
    public void onClick_Enter(View view) {
        switch (view.getId()) {
            case R.id.login: // 로그인 버튼
                // 계정 db 에 등록된 아이디 불러오는 쿼리 날리기
                // if(아이디가 없을 경우 || 비밀번호가 일치하지 않을 경우 ){
//                   Toast.makeText(this, "아이디 또는 비밀번호를 확인해주세요.",Toast.LENGTH_SHORT).show();
//                  }
                // else{
                // 아이디의 주차 여부 검사
                // if(주차여부 == false){
                    Intent logIntent = new Intent(this, ParkListActivity.class);
                    startActivity(logIntent);
                    finish();
//                     }
//                else{
//                  Intent logIntent = new Intent(this, ParkingLot.class);
//                    startActivity(logIntent);
//                    finish();
//                  }

    //        }
                break;
            case R.id.join: // 회원가입 버튼
                LinearLayout dialogView = (LinearLayout) View.inflate(
                        this, R.layout.layout_join_dialog, null);
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
                    EditText ID = (EditText)findViewById(R.id.setId);
                    EditText PW = (EditText)findViewById(R.id.setPw);
                    EditText CONTACT = (EditText)findViewById(R.id.setContact);

//                    "INSERT INTO memberInfo (memberID, memberPW, memberContact) VALUES (\"" + ID + "\", \"" + PW + "\", \"" + CONTACT + "\");"



                    // 회원정보 테이블에 insert 쿼리 날리기
                    Toast.makeText(MainActivity.this, "가입되었습니다.", Toast.LENGTH_SHORT).show();
                    break;
                case DialogInterface.BUTTON_NEGATIVE: // 취소
                    Toast.makeText(MainActivity.this, "취소", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}
