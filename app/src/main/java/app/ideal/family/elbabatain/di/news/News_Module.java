package app.ideal.family.elbabatain.di.news;

import android.app.Application;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import app.ideal.family.elbabatain.network.news.NewsApi;
import app.ideal.family.elbabatain.ui.news.News_Adapter;
import app.ideal.family.elbabatain.ui.news.OnlyOneNews_Activity;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class News_Module {

    @NewsScope
    @Provides
    static NewsApi providerNewsApi(Retrofit retrofit){
        return retrofit.create(NewsApi.class);
    }

    @NewsScope
    @Provides
    static News_Adapter providerNewsAdapter(){
        return new News_Adapter();
    }

    @NewsScope
    @Provides
    static LinearLayoutManager providerLinearLayoutManager(Application application){
        return new LinearLayoutManager(application);
    }

    @NewsScope
    @Provides
    static Bundle providerBundle (){
        return new Bundle();
    }

    @NewsScope
    @Provides
    static OnlyOneNews_Activity providerOnlyOneNews_fragment(){
        return new OnlyOneNews_Activity();
    }
}
