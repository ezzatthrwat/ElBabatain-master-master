package app.ideal.family.elbabatain.di.events;

import android.app.Application;

import androidx.recyclerview.widget.LinearLayoutManager;

import app.ideal.family.elbabatain.network.events.EventsApi;
import app.ideal.family.elbabatain.ui.events.Events_Adapter;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class Event_Module {

    @EventScope
    @Provides
    static EventsApi providersEventsApi(Retrofit retrofit){

        return retrofit.create(EventsApi.class);
    }


    @EventScope
    @Provides
    static Events_Adapter providerEvents_adapter(){
        return new Events_Adapter();
    }

    @EventScope
    @Provides
    static LinearLayoutManager providerLinearLayoutManager(Application application){
        return new LinearLayoutManager(application);
    }


}
