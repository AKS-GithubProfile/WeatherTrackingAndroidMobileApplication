package Com.Project.WeatherTrackingAndroidMobileApplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
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

import Com.Project.WeatherTrackingAndroidMobileApplication.Main_Interface.Main_DataInterface;
import Com.Project.WeatherTrackingAndroidMobileApplication.Main_Model.Main_DataBindingModel;
import Com.Project.WeatherTrackingAndroidMobileApplication.Main_Model.Main_DataModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity_01 extends AppCompatActivity implements LocationListener {

    TextView TextView_31, TextView_32, TextView_33, TextView_34, TextView_37, TextView_39, TextView_41, TextView_46,
            TextView_47, TextView_49, TextView_45;
    EditText EditText_12;
    RelativeLayout RelativeLayout_44;
    ImageView ImageView_20, ImageView_21, ImageView_31;
    LocationManager LocationManager_01;

    String ApiID = "c45b636be0e5d276d271a689b79405c1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_01);

        TextView_31 = findViewById(R.id.TextView_31);
        TextView_33 = findViewById(R.id.TextView_33);
        TextView_34 = findViewById(R.id.TextView_34);
        TextView_37 = findViewById(R.id.TextView_37);
        TextView_39 = findViewById(R.id.TextView_39);
        TextView_41 = findViewById(R.id.TextView_41);
        TextView_46 = findViewById(R.id.TextView_46);
        TextView_47 = findViewById(R.id.TextView_47);
        TextView_49 = findViewById(R.id.TextView_49);
        TextView_45 = findViewById(R.id.TextView_45);
        EditText_12 = findViewById(R.id.EditText_12);

        if (ContextCompat.checkSelfPermission(MainActivity_01.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity_01.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }

        TextView_32 = findViewById(R.id.TextView_32);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("dd MMMM, yyyy");
        String CurrentDate = SimpleDateFormat.format(new Date());
        TextView_32.setText(CurrentDate);

        ImageView_20 = findViewById(R.id.ImageView_20);
        RelativeLayout_44 = findViewById(R.id.RelativeLayout_44);
        ImageView_20.setOnClickListener(v -> {
            GetWeatherData();
            RelativeLayout_44.setVisibility(View.VISIBLE);
            new Handler().postDelayed(() -> RelativeLayout_44.setVisibility(View.INVISIBLE), 3000);
        });

        ImageView_21 = findViewById(R.id.ImageView_21);
        ImageView_21.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity_01.this, UserActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right_animation, R.anim.slide_out_left_animation);
            finish();
        });

        ImageView_31 = findViewById(R.id.ImageView_31);
        ImageView_31.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity_01.this, MainActivity_02.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left_animation, R.anim.slide_out_right_animation);
            finish();
        });
    }

    @Override
    protected void onStart() {
        GetCountryData();
        new AlertDialog.Builder(this).setTitle("Weather Details").setMessage("Proper Location Required To View Weather Details.").
                setNegativeButton("Ok", null).show();
        super.onStart();
    }

    public void GetWeatherData() {
        Retrofit Retrofit = new Retrofit.Builder().baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        Main_DataInterface MyApi = Retrofit.create(Main_DataInterface.class);
        Call<Main_DataBindingModel> DataBindingModelCall_01 = MyApi.GetWeather(EditText_12.getText().toString().trim(), ApiID);
        DataBindingModelCall_01.enqueue(new Callback<Main_DataBindingModel>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<Main_DataBindingModel> call, @NonNull Response<Main_DataBindingModel> response) {
                if (response.code() == 404) {
                    Toast.makeText(MainActivity_01.this, "Fill proper Location !!", Toast.LENGTH_LONG).show();
                } else if (!(response.isSuccessful())) {
                    Toast.makeText(MainActivity_01.this, response.code() + " ", Toast.LENGTH_LONG).show();
                    return;
                }
                Main_DataBindingModel MyData = response.body();
                assert MyData != null;
                Main_DataModel Main_DataModel = MyData.getMain();

                String Location_01 = EditText_12.getText().toString();
                TextView_31.setText(Location_01);

                Double Temp_01 = Main_DataModel.getTemp();
                Integer Temperature_01 = (int) (Temp_01 - 273.15);
                TextView_33.setText(Temperature_01 + "°C");

                Double Temp_02 = Main_DataModel.getTemp();
                Integer Temperature_02 = (int) (Temp_02 - 273.15);
                TextView_37.setText(Temperature_02 + "°C");

                Double TempMin = Main_DataModel.getTempMin();
                Integer Temperature_03 = (int) (TempMin - 273.15);
                TextView_39.setText(Temperature_03 + "°C");

                Double TempMax = Main_DataModel.getTempMax();
                Integer Temperature_04 = (int) (TempMax - 273.15);
                TextView_41.setText(Temperature_04 + "°C");

                Integer Humidity = Main_DataModel.getHumidity();
                TextView_46.setText(Humidity + "%");

                Double FeelsLike = Main_DataModel.getFeels_Like();
                Integer Temperature_05 = (int) (FeelsLike - 273.15);
                TextView_47.setText(Temperature_05 + "°C");

                Integer Pressure = Main_DataModel.getPressure();
                TextView_49.setText(Pressure + "%");
            }

            @Override
            public void onFailure(@NonNull Call<Main_DataBindingModel> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity_01.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @SuppressLint("MissingPermission")
    private void GetCountryData() {
        try {
            LocationManager_01 = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            LocationManager_01.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, MainActivity_01.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location Location) {
        try {
            Geocoder Geocoder = new Geocoder(MainActivity_01.this, Locale.getDefault());
            List<Address> AddressData = Geocoder.getFromLocation(Location.getLatitude(), Location.getLongitude(), 1);
            String AreaName_01 = AddressData.get(0).getAdminArea();
            TextView_31.setText(AreaName_01);

            String LocalityName_01 = AddressData.get(0).getLocality();
            TextView_34.setText(LocalityName_01);

            EditText_12.setText(LocalityName_01, TextView.BufferType.EDITABLE);
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
