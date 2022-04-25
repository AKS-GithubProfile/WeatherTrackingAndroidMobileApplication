package Com.Project.WeatherTrackingAndroidMobileApplication;

import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    RelativeLayout RelativeLayout_04, RelativeLayout_06, RelativeLayout_07;
    TextView TextView_02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FirebaseUser User = FirebaseAuth.getInstance().getCurrentUser();

        RelativeLayout_04 = findViewById(R.id.RelativeLayout_04);
        RelativeLayout_04.setOnClickListener(v -> new AlertDialog.Builder(this).setTitle("Notification Box").setMessage("No Notifications Till Now.").setNegativeButton("Ok", null).show());

        TextView_02 = findViewById(R.id.TextView_02);
        TextView_02.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, MainActivity_01.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right_animation, R.anim.slide_out_left_animation);
            finish();
            Toast.makeText(HomeActivity.this, "You need to login for more features !!", Toast.LENGTH_LONG).show();
        });

        RelativeLayout_06 = findViewById(R.id.RelativeLayout_06);
        RelativeLayout_06.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, RegisterActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right_animation, R.anim.slide_out_left_animation);
            finish();
        });

        RelativeLayout_07 = findViewById(R.id.RelativeLayout_07);
        RelativeLayout_07.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right_animation, R.anim.slide_out_left_animation);
            finish();
        });

        if (User != null) {
            Intent intent = new Intent(HomeActivity.this, MainActivity_01.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein_animation, R.anim.fadeout_animation);
            finish();
        }
    }

    @Override
    public void onBackPressed() {

    }
}
