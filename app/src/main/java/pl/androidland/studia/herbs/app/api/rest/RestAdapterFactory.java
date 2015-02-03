package pl.androidland.studia.herbs.app.api.rest;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;


public class RestAdapterFactory {

    private static final String ENDPOINT_URI = "http://swdherbs.hostingasp.pl";

    public static RestAdapter createRestAdapter() {
        return new RestAdapter.Builder()
                .setEndpoint(ENDPOINT_URI)
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestInterceptor.RequestFacade request) {
                        request.addHeader("Accept", "*/*");
                        request.addHeader("Content-Type", "application/json");
                    }
                })
                .build();
    }
}
