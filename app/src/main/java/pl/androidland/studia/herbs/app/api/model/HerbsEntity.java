package pl.androidland.studia.herbs.app.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HerbsEntity {
    @SerializedName("Herb")
    private HerbsDetail herbsDetail;

    @SerializedName("Accuracy")
    private double accuracy;

    @SerializedName("Shops")
    private List<ShopDetail> shops;

    public HerbsDetail getHerbsDetail() {
        return herbsDetail;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public List<ShopDetail> getShops() {
        return shops;
    }
}
