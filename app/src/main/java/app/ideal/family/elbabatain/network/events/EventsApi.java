package app.ideal.family.elbabatain.network.events;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EventsApi {

    @GET("event")
    Call<List<Events_Model>> getAllEventFromApi() ;

    @GET("event/{Date}")
    Call<List<Events_Model>> getAllEventsByDateFromApi(@Path("Date") String Date);
}
