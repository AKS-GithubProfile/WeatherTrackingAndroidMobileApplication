package Com.Project.WeatherTrackingAndroidMobileApplication;

import android.annotation.SuppressLint;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    ImageView ImageView_01;
    ProgressBar ProgressBar_01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ProgressBar_01 = findViewById(R.id.ProgressBar_01);
        ProgressBar_01.setOnClickListener(v -> {
            finish();
            System.exit(0);
        });

        Thread splashThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(5000);
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    overridePendingTransition(R.anim.fadein_animation, R.anim.fadeout_animation);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        splashThread.start();
        ImageView_01 = findViewById(R.id.ImageView_01);
        ImageView_01.setOnClickListener(v -> {
            finish();
            System.exit(0);
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right_animation, R.anim.slide_out_left_animation);
        finish();
    }
}
