package pl.androidland.studia.herbs.app.api.rest;

import retrofit.RestAdapter;


public class HerbsServiceFactory {
    public static HerbsService createHerbsService() {
        RestAdapter adapter = RestAdapterFactory.createRestAdapter();
        return adapter.create(HerbsService.class);
    }
}
