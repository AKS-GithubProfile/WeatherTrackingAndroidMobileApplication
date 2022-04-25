package Com.Project.WeatherTrackingAndroidMobileApplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText EditText_09, EditText_10, EditText_11;
    RelativeLayout RelativeLayout_37, RelativeLayout_39, RelativeLayout_38;
    String EmailPattern_03 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    FirebaseAuth firebaseAuthForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        EditText_09 = findViewById(R.id.EditText_09);
        EditText_10 = findViewById(R.id.EditText_10);
        EditText_11 = findViewById(R.id.EditText_11);
        RelativeLayout_38 = findViewById(R.id.RelativeLayout_38);

        firebaseAuthForgotPassword = FirebaseAuth.getInstance();

        RelativeLayout_39 = findViewById(R.id.RelativeLayout_39);
        RelativeLayout_39.setOnClickListener(v -> {
            RelativeLayout_38.setVisibility(View.INVISIBLE);
            overridePendingTransition(R.anim.fadein_animation, R.anim.fadeout_animation);
        });

        RelativeLayout_37 = findViewById(R.id.RelativeLayout_37);
        RelativeLayout_37.setOnClickListener(v -> ResetPassword_01());
    }

    private void ResetPassword_01() {

        String Location_02 = EditText_09.getText().toString();
        String Email_ID_03 = EditText_10.getText().toString();
        String Full_Name_03 = EditText_11.getText().toString();

        if (TextUtils.isEmpty(Location_02)) {
            EditText_09.setError("Error");
            Toast.makeText(this, "Fill properly the Location section !!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Email_ID_03)) {
            EditText_10.setError("Error");
            Toast.makeText(this, "Fill properly the Email ID section !!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Full_Name_03)) {
            EditText_11.setError("Error");
            Toast.makeText(this, "Fill properly the Full Name section !!", Toast.LENGTH_SHORT).show();
        } else {
            ResetPassword_02();
        }
    }

    private void ResetPassword_02() {

        String Email_ID_03 = EditText_10.getText().toString();

        if (!Email_ID_03.matches(EmailPattern_03)) {
            EditText_10.setError("Error");
            Toast.makeText(this, "Please enter a valid Email ID !!", Toast.LENGTH_SHORT).show();
        } else {
            firebaseAuthForgotPassword.sendPasswordResetEmail(Email_ID_03).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    RelativeLayout_38.setVisibility(View.VISIBLE);
                } else {
                    RelativeLayout_38.setVisibility(View.INVISIBLE);
                    String error = Objects.requireNonNull(task.getException()).getMessage();
                    Toast.makeText(ForgotPasswordActivity.this, error, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left_animation, R.anim.slide_out_right_animation);
        finish();
    }
}
