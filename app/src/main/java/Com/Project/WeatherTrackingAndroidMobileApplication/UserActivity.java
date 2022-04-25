package Com.Project.WeatherTrackingAndroidMobileApplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class UserActivity extends AppCompatActivity {

    TextView TextView_52, TextView_54, TextView_56;
    FirebaseFirestore FirebaseFirestoreUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        FirebaseFirestoreUser = FirebaseFirestore.getInstance();

        TextView_52 = findViewById(R.id.TextView_52);
        TextView_54 = findViewById(R.id.TextView_54);
        TextView_56 = findViewById(R.id.TextView_56);

        FirebaseFirestoreUser.collection("ALL USERS").document(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                .get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                String FullName = task.getResult().getString("Full Name");
                TextView_52.setText(FullName);
                String Location = task.getResult().getString("Location");
                TextView_54.setText(Location);
                String EmailID = task.getResult().getString("Email ID");
                TextView_56.setText(EmailID);
            } else {
                Toast.makeText(UserActivity.this, "Data not exist in our Database !!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(UserActivity.this, MainActivity_01.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left_animation, R.anim.slide_out_right_animation);
        finish();
    }
}
