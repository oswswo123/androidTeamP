package io.github.cheesecat47.ucantfindp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/*
    스플래시 화면입니다.
 */

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, ParkListActivity.class);
        intent.putExtra("state", "launch");
        startActivity(intent);
        finish();
    }
}
