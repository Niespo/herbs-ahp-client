package pl.androidland.studia.herbs.app.api.model.request;

import java.util.Map;


public class HerbsChoiceProvider {
    private final HerbsChoiceWrapper herbsChoiceWrapper;

    public HerbsChoiceWrapper getHerbsChoiceWrapper() {
        return herbsChoiceWrapper;
    }

    private HerbsChoiceProvider(Builder builder) {
        herbsChoiceWrapper = builder.herbsChoiceWrapper;
    }

    public static class Builder {
        public HerbsChoiceWrapper herbsChoiceWrapper;

        public Builder withPreferencesChoice(Map<String, Integer> preferences) {
                herbsChoiceWrapper = convertToPriorityChoice(preferences);
            return this;
        }

        private HerbsChoiceWrapper convertToPriorityChoice(Map<String, Integer> preferenceMap) {
            HerbsChoiceWrapper choice = new HerbsChoiceWrapper();
            for (Map.Entry<String, Integer> preference : preferenceMap.entrySet())
               choice.addHerbsPriority(new HerbChoice(Integer.parseInt(preference.getKey()), preference.getValue()));
            return choice;
        }

        public HerbsChoiceProvider build() {
            return new HerbsChoiceProvider(this);
        }

    }

}
