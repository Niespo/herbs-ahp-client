package pl.androidland.studia.herbs.app.api.model;

import com.google.gson.annotations.SerializedName;

public class HerbsPriority {

    @SerializedName("effect")
    private int effect;
    @SerializedName("value")
    private int value;

    public HerbsPriority(int effect, int value) {
        this.effect = effect;
        this.value = value;
    }
}
