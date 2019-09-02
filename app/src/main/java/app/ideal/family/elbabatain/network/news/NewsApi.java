package app.ideal.family.elbabatain.network.news;

import java.util.List;

import app.ideal.family.elbabatain.network.contain.Contain_Model;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewsApi {

    @GET("post")
    Call<Request_Paging> getNews (@Query("page") int pageNumber) ;

}
