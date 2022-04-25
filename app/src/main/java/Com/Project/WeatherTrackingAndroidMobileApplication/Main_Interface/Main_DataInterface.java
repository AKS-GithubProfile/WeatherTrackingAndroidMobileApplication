package Com.Project.WeatherTrackingAndroidMobileApplication.Main_Interface;

import Com.Project.WeatherTrackingAndroidMobileApplication.Main_Model.Main_DataBindingModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Main_DataInterface {
    @GET("weather")
    Call<Main_DataBindingModel> GetWeather(@Query("q") String CityName,
                                           @Query("appid") String ApiID);
}
