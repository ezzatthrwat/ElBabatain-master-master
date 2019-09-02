package app.ideal.family.elbabatain.network.contain;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ContainApi {

    @GET("content/{id}")
    Call<List<Contain_Model>> getHeaderContain (@Path("id") int id) ;
}
