package pl.androidland.studia.herbs.app.api.model.response.herb;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HerbEntity {
    @SerializedName("Herb")
    private HerbDetail herbDetail;

    @SerializedName("Accuracy")
    private double accuracy;

    @SerializedName("Shops")
    private List<ShopDetail> shops;

    public HerbDetail getHerbDetail() {
        return herbDetail;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public List<ShopDetail> getShops() {
        return shops;
    }
}
