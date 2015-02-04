package pl.androidland.studia.herbs.app.api.model.request;

import com.google.gson.annotations.SerializedName;

public class HerbChoice {

    @SerializedName("effect")
    private int effect;
    @SerializedName("value")
    private int value;

    public HerbChoice(int effect, int value) {
        this.effect = effect;
        this.value = value;
    }
}
