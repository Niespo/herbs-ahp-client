package pl.androidland.studia.herbs.app.api.rest;

import pl.androidland.studia.herbs.app.api.model.HerbsCriterion;
import pl.androidland.studia.herbs.app.api.model.HerbsEntity;
import pl.androidland.studia.herbs.app.api.model.HerbsPriorityChoice;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;

import java.util.List;

public interface HerbsService {
    @GET("/herb/criteria")
    void getCriteriaList(Callback<List<HerbsCriterion>> callback);

    @Headers({
            "Content-type: application/json"
    })
    @POST("/herb/choose")
    void findHerbs(@Body HerbsPriorityChoice choice, Callback<List<HerbsEntity>> chosenHerbs);
}
