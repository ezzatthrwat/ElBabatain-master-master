package app.ideal.family.elbabatain.di.main;

import android.app.Application;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import app.ideal.family.elbabatain.network.main.MainApi;
import app.ideal.family.elbabatain.ui.main.GridRecyclerViewAdapter;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class Main_Model {


    @MainScope
    @Provides
    static MainApi provideMainApi(Retrofit retrofit){
        return retrofit.create(MainApi.class);
    }


    @MainScope
    @Provides
    static GridRecyclerViewAdapter provideAdapter(){
        return new GridRecyclerViewAdapter();
    }

    @MainScope
    @Provides
    static GridLayoutManager provideGridLayoutManager(Application application){
        return new GridLayoutManager(application, 3 , GridLayoutManager.HORIZONTAL, false);
    }



}
