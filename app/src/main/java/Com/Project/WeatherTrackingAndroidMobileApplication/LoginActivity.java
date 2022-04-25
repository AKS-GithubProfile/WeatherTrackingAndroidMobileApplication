package Com.Project.WeatherTrackingAndroidMobileApplication;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    TextView TextView_05, TextView_11;
    RelativeLayout RelativeLayout_09, RelativeLayout_15, RelativeLayout_14, RelativeLayout_16;
    EditText EditText_01, EditText_02, EditText_03;
    ImageView ImageView_05, ImageView_06;
    String EmailPattern_01 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    FirebaseAuth FirebaseAuthLogin;
    FirebaseFirestore FirebaseFirestoreLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText_01 = findViewById(R.id.EditText_01);
        EditText_02 = findViewById(R.id.EditText_02);
        EditText_03 = findViewById(R.id.EditText_03);
        RelativeLayout_16 = findViewById(R.id.RelativeLayout_16);

        FirebaseAuthLogin = FirebaseAuth.getInstance();
        FirebaseFirestoreLogin = FirebaseFirestore.getInstance();

        TextView_05 = findViewById(R.id.TextView_05);
        TextView_05.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        RelativeLayout_09 = findViewById(R.id.RelativeLayout_09);
        RelativeLayout_09.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        RelativeLayout_15 = findViewById(R.id.RelativeLayout_15);
        RelativeLayout_15.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, MainActivity_01.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right_animation, R.anim.slide_out_left_animation);
            finish();
            Toast.makeText(LoginActivity.this, "You need to login for more features !!", Toast.LENGTH_LONG).show();
        });

        ImageView_05 = findViewById(R.id.ImageView_05);
        ImageView_05.setOnClickListener(v -> {
            EditText_03.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            ImageView_05.setVisibility(View.INVISIBLE);
            ImageView_06.setVisibility(View.VISIBLE);
        });

        ImageView_06 = findViewById(R.id.ImageView_06);
        ImageView_06.setOnClickListener(v -> {
            EditText_03.setTransformationMethod(PasswordTransformationMethod.getInstance());
            ImageView_06.setVisibility(View.INVISIBLE);
            ImageView_05.setVisibility(View.VISIBLE);
        });

        TextView_11 = findViewById(R.id.TextView_11);
        TextView_11.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right_animation, R.anim.slide_out_left_animation);
            finish();
        });

        RelativeLayout_14 = findViewById(R.id.RelativeLayout_14);
        RelativeLayout_14.setOnClickListener(v -> LoginAccount_01());
    }

    private void LoginAccount_01() {

        String Full_Name_01 = EditText_01.getText().toString();
        String Email_ID_01 = EditText_02.getText().toString();
        String Password_01 = EditText_03.getText().toString();

        if (TextUtils.isEmpty(Full_Name_01)) {
            EditText_01.setError("Error");
            Toast.makeText(this, "Fill properly the Full Name Field..", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Email_ID_01)) {
            EditText_02.setError("Error");
            Toast.makeText(this, "Fill properly the Email ID Field..", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Password_01)) {
            EditText_03.setError("Error");
            Toast.makeText(this, "Fill properly the Password Field..", Toast.LENGTH_SHORT).show();

        } else {
            LoginAccount_02();
        }
    }

    private void LoginAccount_02() {

        String Email_ID_01 = EditText_02.getText().toString();
        String Password_01 = EditText_03.getText().toString();

        if (!Email_ID_01.matches(EmailPattern_01)) {
            EditText_02.setError("Error");
            Toast.makeText(this, "Please enter a valid Email ID !!", Toast.LENGTH_SHORT).show();
        } else if (Password_01.length() <= 7) {
            EditText_03.setError("Error");
            Toast.makeText(this, "You have to enter at least 7 digits in the Password section !!", Toast.LENGTH_SHORT).show();
        } else {
            RelativeLayout_16.setVisibility(View.VISIBLE);
            FirebaseAuthLogin.signInWithEmailAndPassword(Email_ID_01, Password_01).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity_01.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right_animation, R.anim.slide_out_left_animation);
                    finish();
                    Toast.makeText(LoginActivity.this, "Login Successfully !!", Toast.LENGTH_LONG).show();
                } else {
                    RelativeLayout_16.setVisibility(View.INVISIBLE);
                    String error = Objects.requireNonNull(task.getException()).getMessage();
                    Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left_animation, R.anim.slide_out_right_animation);
        finish();
    }
}
