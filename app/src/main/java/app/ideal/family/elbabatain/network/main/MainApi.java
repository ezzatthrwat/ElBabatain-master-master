package app.ideal.family.elbabatain.network.main;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainApi {


    @GET("header")
    Call<List<HeaderExample_Model>> getUserPost();


    @GET("img")
    Call<List<AdsImg_model>> getAdsImg();


}
