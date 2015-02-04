package pl.androidland.studia.herbs.app.api.model.request;

import com.google.common.collect.Lists;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HerbsEffectsWrapper {
    @SerializedName("effects")
    private List<HerbEffect> effects;

    public HerbsEffectsWrapper() {
        effects = Lists.newArrayList();
    }

    public void addHerbsPriority(HerbEffect herbEffect) {
        effects.add(herbEffect);
    }
}
