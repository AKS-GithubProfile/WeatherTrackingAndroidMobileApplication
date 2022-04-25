package Com.Project.WeatherTrackingAndroidMobileApplication.Main_Model;

import com.google.gson.annotations.SerializedName;

public class Main_DataBindingModel {
    @SerializedName("main")
    Main_DataModel Main_DataModel;

    public Main_DataModel getMain() {
        return Main_DataModel;
    }

    public void setMain(Main_DataModel Main_DataModel) {
        this.Main_DataModel = Main_DataModel;
    }
}
