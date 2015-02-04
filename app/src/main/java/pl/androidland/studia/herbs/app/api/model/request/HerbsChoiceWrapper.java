package pl.androidland.studia.herbs.app.api.model.request;

import com.google.common.collect.Lists;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HerbsChoiceWrapper {
    @SerializedName("effects")
    private List<HerbChoice> effects;

    public HerbsChoiceWrapper() {
        effects = Lists.newArrayList();
    }

    public void addHerbsPriority(HerbChoice herbChoice) {
        effects.add(herbChoice);
    }
}
