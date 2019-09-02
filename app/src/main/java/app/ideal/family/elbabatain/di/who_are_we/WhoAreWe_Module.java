package app.ideal.family.elbabatain.di.who_are_we;

import app.ideal.family.elbabatain.di.contain.ContainScope;
import app.ideal.family.elbabatain.network.contain.ContainApi;
import app.ideal.family.elbabatain.network.who_are_we.WhoAreWe_Api;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class WhoAreWe_Module {

    @WhoAreWeScope
    @Provides
    static WhoAreWe_Api provideAreWeApi(Retrofit retrofit) {
        return retrofit.create(WhoAreWe_Api.class);
    }
}
