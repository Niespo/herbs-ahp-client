package pl.androidland.studia.herbs.app.api.model.request;

import com.google.gson.annotations.SerializedName;

public class HerbEffect {

    @SerializedName("effect")
    private int effect;
    @SerializedName("value")
    private int value;

    public HerbEffect(int effect, int value) {
        this.effect = effect;
        this.value = value;
    }
}
