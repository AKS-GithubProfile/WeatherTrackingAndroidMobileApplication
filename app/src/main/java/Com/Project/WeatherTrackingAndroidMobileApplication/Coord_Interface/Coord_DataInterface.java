package Com.Project.WeatherTrackingAndroidMobileApplication.Coord_Interface;

import Com.Project.WeatherTrackingAndroidMobileApplication.Coord_Model.Coord_DataBindingModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Coord_DataInterface {
    @GET("weather")
    Call<Coord_DataBindingModel> GetLocation(@Query("q") String CityName,
                                             @Query("appid") String ApiID);
}
