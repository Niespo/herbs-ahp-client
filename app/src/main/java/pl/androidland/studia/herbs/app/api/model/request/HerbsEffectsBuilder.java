package pl.androidland.studia.herbs.app.api.model.request;

import java.util.Map;


public class HerbsEffectsBuilder {
    private final HerbsEffectsWrapper herbsEffectsWrapper;

    public HerbsEffectsWrapper getHerbsEffectsWrapper() {
        return herbsEffectsWrapper;
    }

    private HerbsEffectsBuilder(Builder builder) {
        herbsEffectsWrapper = builder.herbsEffectsWrapper;
    }

    public static class Builder {
        public HerbsEffectsWrapper herbsEffectsWrapper;

        public Builder withPreferencesChoice(Map<String, Integer> preferences) {
                herbsEffectsWrapper = convertToPriorityChoice(preferences);
            return this;
        }

        private HerbsEffectsWrapper convertToPriorityChoice(Map<String, Integer> preferenceMap) {
            HerbsEffectsWrapper choice = new HerbsEffectsWrapper();
            for (Map.Entry<String, Integer> preference : preferenceMap.entrySet())
               choice.addHerbsPriority(new HerbEffect(Integer.parseInt(preference.getKey()), preference.getValue()));
            return choice;
        }

        public HerbsEffectsBuilder build() {
            return new HerbsEffectsBuilder(this);
        }

    }

}
