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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    TextView TextView_16;
    RelativeLayout RelativeLayout_20, RelativeLayout_28, RelativeLayout_27, RelativeLayout_29;
    EditText EditText_04, EditText_05, EditText_06, EditText_07, EditText_08;
    ImageView ImageView_10, ImageView_11, ImageView_12, ImageView_13;
    String EmailPattern_02 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    FirebaseAuth FirebaseAuthRegister;
    FirebaseFirestore FirebaseFirestoreRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText_04 = findViewById(R.id.EditText_04);
        EditText_05 = findViewById(R.id.EditText_05);
        EditText_06 = findViewById(R.id.EditText_06);
        EditText_07 = findViewById(R.id.EditText_07);
        EditText_08 = findViewById(R.id.EditText_08);
        RelativeLayout_29 = findViewById(R.id.RelativeLayout_29);

        FirebaseAuthRegister = FirebaseAuth.getInstance();
        FirebaseFirestoreRegister = FirebaseFirestore.getInstance();

        RelativeLayout_20 = findViewById(R.id.RelativeLayout_20);
        RelativeLayout_20.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        TextView_16 = findViewById(R.id.TextView_16);
        TextView_16.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        RelativeLayout_28 = findViewById(R.id.RelativeLayout_28);
        RelativeLayout_28.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, MainActivity_01.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right_animation, R.anim.slide_out_left_animation);
            finish();
            Toast.makeText(RegisterActivity.this, "You need to login for more features !!", Toast.LENGTH_LONG).show();
        });

        ImageView_10 = findViewById(R.id.ImageView_10);
        ImageView_10.setOnClickListener(v -> {
            EditText_07.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            ImageView_10.setVisibility(View.INVISIBLE);
            ImageView_11.setVisibility(View.VISIBLE);
        });

        ImageView_11 = findViewById(R.id.ImageView_11);
        ImageView_11.setOnClickListener(v -> {
            EditText_07.setTransformationMethod(PasswordTransformationMethod.getInstance());
            ImageView_11.setVisibility(View.INVISIBLE);
            ImageView_10.setVisibility(View.VISIBLE);
        });

        ImageView_12 = findViewById(R.id.ImageView_12);
        ImageView_12.setOnClickListener(v -> {
            EditText_08.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            ImageView_12.setVisibility(View.INVISIBLE);
            ImageView_13.setVisibility(View.VISIBLE);
        });

        ImageView_13 = findViewById(R.id.ImageView_13);
        ImageView_13.setOnClickListener(v -> {
            EditText_08.setTransformationMethod(PasswordTransformationMethod.getInstance());
            ImageView_13.setVisibility(View.INVISIBLE);
            ImageView_12.setVisibility(View.VISIBLE);
        });

        RelativeLayout_27 = findViewById(R.id.RelativeLayout_27);
        RelativeLayout_27.setOnClickListener(v -> RegisterAccount_01());
    }

    private void RegisterAccount_01() {

        String Full_Name_02 = EditText_04.getText().toString();
        String Location_01 = EditText_05.getText().toString();
        String Email_ID_02 = EditText_06.getText().toString();
        String Password_02 = EditText_07.getText().toString();
        String Confirm_Password_01 = EditText_08.getText().toString();

        if (TextUtils.isEmpty(Full_Name_02)) {
            EditText_04.setError("Error");
            Toast.makeText(this, "Fill properly the Full Name section !!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Location_01)) {
            EditText_05.setError("Error");
            Toast.makeText(this, "Fill properly the Location section !!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Email_ID_02)) {
            EditText_06.setError("Error");
            Toast.makeText(this, "Fill properly the Email ID section !!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Password_02)) {
            EditText_07.setError("Error");
            Toast.makeText(this, "Fill properly the Password section !!", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(Confirm_Password_01)) {
            EditText_08.setError("Error");
            Toast.makeText(this, "Fill properly the Confirm Password section !!", Toast.LENGTH_SHORT).show();
        } else {
            RegisterAccount_02();
        }
    }

    private void RegisterAccount_02() {

        String Full_Name_02 = EditText_04.getText().toString();
        String Location_01 = EditText_05.getText().toString();
        String Email_ID_02 = EditText_06.getText().toString();
        String Password_02 = EditText_07.getText().toString();
        String Confirm_Password_01 = EditText_08.getText().toString();

        if (!Email_ID_02.matches(EmailPattern_02)) {
            EditText_06.setError("Error");
            Toast.makeText(this, "Please enter a valid Email ID !!", Toast.LENGTH_SHORT).show();
        } else if (Password_02.length() <= 7) {
            EditText_07.setError("Error");
            Toast.makeText(this, "You have to enter at least 7 digits in the Password section !!", Toast.LENGTH_SHORT).show();
        } else if (!Confirm_Password_01.equals(Password_02)) {
            EditText_08.setError("Error");
            Toast.makeText(this, "Your Password and Confirm Password does not match !!", Toast.LENGTH_SHORT).show();
        } else {
            RelativeLayout_29.setVisibility(View.VISIBLE);
            FirebaseAuthRegister.createUserWithEmailAndPassword(Email_ID_02, Password_02).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Map<Object, String> userdataMap = new HashMap<>();
                    userdataMap.put("Full Name", Full_Name_02);
                    userdataMap.put("Location", Location_01);
                    userdataMap.put("Email ID", Email_ID_02);
                    userdataMap.put("Password", Password_02);
                    FirebaseFirestoreRegister.collection("ALL USERS").add(userdataMap)
                            .addOnCompleteListener(task1 -> {
                                if (task1.isSuccessful()) {
                                    Intent intent = new Intent(RegisterActivity.this, MainActivity_01.class);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.slide_in_right_animation, R.anim.slide_out_left_animation);
                                    finish();
                                    Toast.makeText(RegisterActivity.this, "Register Successfully !!", Toast.LENGTH_LONG).show();
                                } else {
                                    RelativeLayout_29.setVisibility(View.INVISIBLE);
                                    String error = Objects.requireNonNull(task1.getException()).getMessage();
                                    Toast.makeText(RegisterActivity.this, error, Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    RelativeLayout_29.setVisibility(View.INVISIBLE);
                    String error = Objects.requireNonNull(task.getException()).getMessage();
                    Toast.makeText(RegisterActivity.this, error, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left_animation, R.anim.slide_out_right_animation);
        finish();
    }
}
