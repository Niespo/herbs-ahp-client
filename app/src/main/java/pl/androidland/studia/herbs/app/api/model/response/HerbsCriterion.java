package pl.androidland.studia.herbs.app.api.model.response;

import com.google.gson.annotations.SerializedName;


public class HerbsCriterion {
    @SerializedName("Name")
    private String name;
    @SerializedName("Effect")
    private String effect;

    private boolean checked = false;

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }

    public String getName() {
        return name;
    }

    public String getEffect() {
        return effect;
    }
}
