package pl.androidland.studia.herbs.app.api.model;

import com.google.gson.annotations.SerializedName;

public class HerbsDetail {
    @SerializedName("Name")
    private String name;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public String getEffect() {
        return effect;
    }

    public String getId() {
        return id;
    }

    @SerializedName("Description")

    private String description;
    @SerializedName("Price")
    private String price;
    @SerializedName("HerbEffects")
    private String effect;
    @SerializedName("Id")
    private String id;
}
