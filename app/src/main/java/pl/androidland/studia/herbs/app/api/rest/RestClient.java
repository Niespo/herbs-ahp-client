package pl.androidland.studia.herbs.app.api.rest;

public class RestClient {

    private final HerbsService service;

    public RestClient() {
        service = HerbsServiceFactory.createHerbsService();
    }

    public HerbsService getService() {
        return service;
    }
}
