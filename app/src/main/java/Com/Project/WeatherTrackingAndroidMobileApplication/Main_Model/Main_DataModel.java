package Com.Project.WeatherTrackingAndroidMobileApplication.Main_Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main_DataModel {
    @SerializedName("temp")
    @Expose
    private Double Temp;

    @SerializedName("pressure")
    @Expose
    private Integer Pressure;

    @SerializedName("humidity")
    @Expose
    private Integer Humidity;

    @SerializedName("temp_min")
    @Expose
    private Double TempMin;

    @SerializedName("temp_max")
    @Expose
    private Double TempMax;

    @SerializedName("feels_like")
    @Expose
    private Double Feels_Like;

    public Double getTemp() {
        return Temp;
    }

    public void setTemp(Double Temp) {
        this.Temp = Temp;
    }

    public Double getFeels_Like() {
        return Feels_Like;
    }

    public void setFeels_Like(Double Feels_Like) {
        this.Feels_Like = Feels_Like;
    }

    public Integer getPressure() {
        return Pressure;
    }

    public void setPressure(Integer Pressure) {
        this.Pressure = Pressure;
    }

    public Integer getHumidity() {
        return Humidity;
    }

    public void setHumidity(Integer Humidity) {
        this.Humidity = Humidity;
    }

    public Double getTempMin() {
        return TempMin;
    }

    public void setTempMin(Double TempMin) {
        this.TempMin = TempMin;
    }

    public Double getTempMax() {
        return TempMax;
    }

    public void setTempMax(Double TempMax) {
        this.TempMax = TempMax;
    }
}
