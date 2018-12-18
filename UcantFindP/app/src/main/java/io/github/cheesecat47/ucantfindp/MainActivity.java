package io.github.cheesecat47.ucantfindp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
                // 계정 db 에 등록된 아이디, 비밀번호와 일치여부 검사

                // 일치하는 아이디, 비밀번호가 없을 경우
//                Toast.makeText(this, "아이디 또는 비밀번호를 확인해주세요.",Toast.LENGTH_SHORT).show();
                // 일치하는 아이디, 비밀번호가 있을 경우
                Intent logIntent = new Intent(this, ParkListActivity.class);
                startActivity(logIntent);
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
                    Toast.makeText(MainActivity.this,
                            "가입되었습니다.", Toast.LENGTH_SHORT).show();
                    break;
                case DialogInterface.BUTTON_NEGATIVE: // 취소
                    Toast.makeText(MainActivity.this,
                            "취소", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
}
