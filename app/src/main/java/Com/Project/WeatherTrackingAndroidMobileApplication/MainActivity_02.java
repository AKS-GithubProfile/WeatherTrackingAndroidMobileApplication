package Com.Project.WeatherTrackingAndroidMobileApplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Com.Project.WeatherTrackingAndroidMobileApplication.Coord_Interface.Coord_DataInterface;
import Com.Project.WeatherTrackingAndroidMobileApplication.Coord_Model.Coord_DataBindingModel;
import Com.Project.WeatherTrackingAndroidMobileApplication.Coord_Model.Coord_DataModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity_02 extends AppCompatActivity implements LocationListener {

    TextView TextView_57, TextView_58, TextView_59, TextView_61, TextView_63;
    EditText EditText_13;
    RelativeLayout RelativeLayout_50;
    ImageView ImageView_33, ImageView_34, ImageView_36;

    LocationManager LocationManager_02;

    String ApiID = "c45b636be0e5d276d271a689b79405c1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_02);

        TextView_57 = findViewById(R.id.TextView_57);
        TextView_59 = findViewById(R.id.TextView_59);
        TextView_61 = findViewById(R.id.TextView_61);
        TextView_63 = findViewById(R.id.TextView_63);
        EditText_13 = findViewById(R.id.EditText_13);

        TextView_58 = findViewById(R.id.TextView_58);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("dd MMMM, yyyy");
        String CurrentDate = SimpleDateFormat.format(new Date());
        TextView_58.setText(CurrentDate);

        ImageView_33 = findViewById(R.id.ImageView_33);
        RelativeLayout_50 = findViewById(R.id.RelativeLayout_50);
        ImageView_33.setOnClickListener(v -> {
            GetLocationData();
            RelativeLayout_50.setVisibility(View.VISIBLE);
            new Handler().postDelayed(() -> RelativeLayout_50.setVisibility(View.INVISIBLE), 3000);
        });

        ImageView_34 = findViewById(R.id.ImageView_34);
        ImageView_34.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity_02.this, UserActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right_animation, R.anim.slide_out_left_animation);
            finish();
        });

        ImageView_36 = findViewById(R.id.ImageView_36);
        ImageView_36.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity_02.this, MainActivity_01.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right_animation, R.anim.slide_out_left_animation);
            finish();
        });
    }

    @Override
    protected void onStart() {
        GetAddressData();
        new AlertDialog.Builder(this).setTitle("Location Details").setMessage("Proper Location Required To View Location Details.").
                setNegativeButton("Ok", null).show();
        super.onStart();
    }

    private void GetLocationData() {
        Retrofit Retrofit = new Retrofit.Builder().baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        Coord_DataInterface MyApi = Retrofit.create(Coord_DataInterface.class);
        Call<Coord_DataBindingModel> DataBindingModelCall_02 = MyApi.GetLocation(EditText_13.getText().toString().trim(), ApiID);
        DataBindingModelCall_02.enqueue(new Callback<Coord_DataBindingModel>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<Coord_DataBindingModel> call, @NonNull Response<Coord_DataBindingModel> response) {
                if (response.code() == 404) {
                    Toast.makeText(MainActivity_02.this, "Fill proper Location !!", Toast.LENGTH_LONG).show();
                } else if (!(response.isSuccessful())) {
                    Toast.makeText(MainActivity_02.this, response.code() + " ", Toast.LENGTH_LONG).show();
                    return;
                }
                Coord_DataBindingModel MyData = response.body();
                assert MyData != null;
                Coord_DataModel Coord_DataModel = MyData.getMain();

                String Location_02 = EditText_13.getText().toString();
                TextView_57.setText(Location_02);
                TextView_59.setText(Location_02);

                Double Latitude_01 = Coord_DataModel.getLatitude();
                TextView_61.setText(Latitude_01 + "N");

                Double Longitude_01 = Coord_DataModel.getLatitude();
                TextView_63.setText(Longitude_01 + "E");
            }

            @Override
            public void onFailure(@NonNull Call<Coord_DataBindingModel> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity_02.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @SuppressLint("MissingPermission")
    private void GetAddressData() {
        try {
            LocationManager_02 = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            LocationManager_02.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, MainActivity_02.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onLocationChanged(@NonNull Location Location) {
        try {
            Geocoder Geocoder = new Geocoder(MainActivity_02.this, Locale.getDefault());
            List<Address> AddressData = Geocoder.getFromLocation(Location.getLatitude(), Location.getLongitude(), 1);
            String AreaName_01 = AddressData.get(0).getAdminArea();
            TextView_57.setText(AreaName_01);

            String LocalityName_01 = AddressData.get(0).getLocality();
            EditText_13.setText(LocalityName_01, TextView.BufferType.EDITABLE);

            String Address_01 = AddressData.get(0).getAddressLine(0);
            TextView_59.setText(Address_01);

            double Latitude_02 = Location.getLatitude();
            TextView_61.setText(Latitude_02 + "N");

            double Longitude_02 = Location.getLatitude();
            TextView_63.setText(Longitude_02 + "E");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }

    @Override
    public void onBackPressed() {

    }
}
