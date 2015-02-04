package pl.androidland.studia.herbs.app.api.model.herb;

import com.google.gson.annotations.SerializedName;


public class HerbDetail {
    @SerializedName("Name")
    private String name;

    @SerializedName("Description")
    private String description;
    @SerializedName("Price")
    private String price;
    @SerializedName("HerbEffects")
    private String effect;
    @SerializedName("Id")
    private String id;

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
}
