package app.ideal.family.elbabatain.di.contain;

import android.app.Application;

import androidx.recyclerview.widget.LinearLayoutManager;

import app.ideal.family.elbabatain.network.contain.ContainApi;
import app.ideal.family.elbabatain.ui.contain.ContainAdapter;
import app.ideal.family.elbabatain.ui.contain.TitleAdapter;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class Contain_module {

    @ContainScope
    @Provides
    static ContainApi provideContainApi(Retrofit retrofit) {
        return retrofit.create(ContainApi.class);
    }

    @ContainScope
    @Provides
    static ContainAdapter provideContainAdapter(){
        return new ContainAdapter();
    }

    @ContainScope
    @Provides
    static TitleAdapter provideTitleAdapter(){
        return new TitleAdapter();
    }

    @ContainScope
    @Provides
    static LinearLayoutManager providerLinearLayoutManager(Application application){
        return new LinearLayoutManager(application);
    }

}
