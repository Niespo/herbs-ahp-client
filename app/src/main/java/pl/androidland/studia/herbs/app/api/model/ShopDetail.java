package pl.androidland.studia.herbs.app.api.model;

import com.google.gson.annotations.SerializedName;

public class ShopDetail {
    @SerializedName("Name")
    private String name;
    @SerializedName("City")
    private String city;
    @SerializedName("Address")
    private String address;
    @SerializedName("Id")
    private String id;

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getId() {
        return id;
    }
}
