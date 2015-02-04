package pl.androidland.studia.herbs.app.api.rest;

public class RestProvider {

    private final HerbsService service;

    public RestProvider() {
        service = HerbsServiceFactory.createHerbsService();
    }

    public HerbsService getService() {
        return service;
    }
}
