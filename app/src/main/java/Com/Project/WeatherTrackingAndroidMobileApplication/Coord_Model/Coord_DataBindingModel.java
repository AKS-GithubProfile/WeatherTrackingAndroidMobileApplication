package Com.Project.WeatherTrackingAndroidMobileApplication.Coord_Model;

import com.google.gson.annotations.SerializedName;

public class Coord_DataBindingModel {
    @SerializedName("coord")
    Coord_DataModel Coord_DataModel;

    public Coord_DataModel getMain() {
        return Coord_DataModel;
    }

    public void setMain(Coord_DataModel Coord_DataModel) {
        this.Coord_DataModel = Coord_DataModel;
    }
}
