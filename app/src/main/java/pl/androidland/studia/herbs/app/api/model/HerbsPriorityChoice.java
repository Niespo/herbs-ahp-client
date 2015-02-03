package pl.androidland.studia.herbs.app.api.model;

import com.google.common.collect.Lists;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HerbsPriorityChoice {
    @SerializedName("effects")
    private List<HerbsPriority> effects;

    public HerbsPriorityChoice() {
        effects = Lists.newArrayList();
    }

    public void addHerbsPriority(HerbsPriority herbsPriority) {
        effects.add(herbsPriority);
    }
}
