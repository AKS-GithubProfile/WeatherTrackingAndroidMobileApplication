package Com.Project.WeatherTrackingAndroidMobileApplication.Coord_Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Coord_DataModel {
    @SerializedName("lat")
    @Expose
    private Double Latitude;

    @SerializedName("lon")
    @Expose
    private Double Longitude;

    public Double getLatitude() {
        return Latitude;
    }

    public void setLatitude(Double Latitude) {
        this.Latitude = Latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public void setLongitude(Double Longitude) {
        this.Longitude = Longitude;
    }
}
