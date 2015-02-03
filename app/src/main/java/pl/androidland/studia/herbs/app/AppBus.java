package pl.androidland.studia.herbs.app;

import android.app.Application;
import pl.androidland.studia.herbs.app.api.model.HerbsPreferences;
import pl.androidland.studia.herbs.app.api.rest.RestClient;


public class AppBus extends Application {

    private static RestClient restClient;
    private static HerbsPreferences herbsPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static RestClient getRestClient() {
        if (restClient == null)
            restClient = new RestClient();
        return restClient;
    }

    public static void setHerbsPreferences(HerbsPreferences preferences) {
        herbsPreferences = preferences;
    }

    public static HerbsPreferences getHerbsPreferences() {
        return herbsPreferences;
    }
}
