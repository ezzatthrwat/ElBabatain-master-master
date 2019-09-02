package app.ideal.family.elbabatain.network.who_are_we;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WhoAreWe_Api {

    @GET("about")
    Call<WhoAreWe_Model> getWhoAreWeRemoteData();
}
