package app.ideal.family.elbabatain.ui.events;

import android.app.Application;
import android.net.sip.SipSession;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import app.ideal.family.elbabatain.AppRepository;
import app.ideal.family.elbabatain.network.events.EventsApi;
import app.ideal.family.elbabatain.network.events.Events_Model;

public class Event_ViewModel extends ViewModel {

    private static final String TAG = "Event_ViewModel";

    private AppRepository appRepository ;

    LiveData<List<Events_Model>> ALLEVENTS ;

    @Inject
    Event_ViewModel(Application application , AppRepository appRepository , EventsApi eventsApi) {

        appRepository.setAppRepository(application
                , null
                ,null
                , null
                , eventsApi
                , null
                , "EventActivity" );


        this.ALLEVENTS = appRepository.getAllEventsFromDatabase();

        this.appRepository = appRepository ;

        Log.e(TAG, "Event_ViewModel: is Working....." );

    }


    LiveData<List<Events_Model>> getAllEventsFromDatabaseByDate(String Date){

        return appRepository.getAllEventsFromDatabaseByDate(Date);
    }
//
//    void getAllEventsByDate(String Date){
//
//        appRepository.getAllEventsByDateFromApi(Date);
//    }
}
