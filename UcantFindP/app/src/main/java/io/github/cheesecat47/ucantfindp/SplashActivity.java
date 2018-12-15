package io.github.cheesecat47.ucantfindp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/*
    스플래시 화면입니다.
 */

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("TAG", "SplashActivity: start");
        Intent intent = new Intent(this, ParkListActivity.class);
        //                                              LogInActivity.class 만들고 그 쪽으로 이동.
        startActivity(intent);
        finish();
    }
}
