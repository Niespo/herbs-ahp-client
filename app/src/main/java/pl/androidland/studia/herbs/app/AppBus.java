package pl.androidland.studia.herbs.app;

import android.app.Application;
import pl.androidland.studia.herbs.app.api.model.request.HerbsChoiceProvider;
import pl.androidland.studia.herbs.app.api.rest.RestProvider;


public class AppBus extends Application {

    private static RestProvider restProvider;
    private static HerbsChoiceProvider herbsChoiceProvider;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static RestProvider getRestProvider() {
        if (restProvider == null)
            restProvider = new RestProvider();
        return restProvider;
    }

    public static void setHerbsChoiceProvider(HerbsChoiceProvider preferences) {
        herbsChoiceProvider = preferences;
    }

    public static HerbsChoiceProvider getHerbsChoiceProvider() {
        return herbsChoiceProvider;
    }
}
