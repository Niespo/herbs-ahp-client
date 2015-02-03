package pl.androidland.studia.herbs.app.api.model;

import org.json.JSONObject;

import java.util.Map;


public class HerbsPreferences {


    private final JSONObject wrappedObject;
    private final HerbsPriorityChoice herbsPriorityChoice;

    public HerbsPriorityChoice getHerbsPriorityChoice() {
        return herbsPriorityChoice;
    }

    private HerbsPreferences(Builder builder) {
        wrappedObject = new JSONObject();
        herbsPriorityChoice = builder.herbsPriorityChoice;
    }




    public JSONObject getWrappedObject() {
        return wrappedObject;
    }

    public static class Builder {

        public HerbsPriorityChoice herbsPriorityChoice;


        public Builder withPreferencesChoice(Map<String, Integer> preferenceMap) {
                herbsPriorityChoice = convertToPriorityChoice(preferenceMap);
            return this;
        }

        private HerbsPriorityChoice convertToPriorityChoice(Map<String, Integer> preferenceMap) {
            HerbsPriorityChoice choice = new HerbsPriorityChoice();
            for (Map.Entry<String, Integer> preference : preferenceMap.entrySet())
               choice.addHerbsPriority(new HerbsPriority(Integer.parseInt(preference.getKey()), preference.getValue()));
            return choice;
        }


        public HerbsPreferences build() {
            return new HerbsPreferences(this);
        }

    }

}
