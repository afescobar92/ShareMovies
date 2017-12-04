package movies.com.co.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class Cinemas implements Serializable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("locationsList")
    @Expose
    private List<LocationCinemas> locationList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LocationCinemas> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<LocationCinemas> locationList) {
        this.locationList = locationList;
    }
}
