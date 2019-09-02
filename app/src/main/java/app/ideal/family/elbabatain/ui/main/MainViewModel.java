package app.ideal.family.elbabatain.ui.main;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import app.ideal.family.elbabatain.AppRepository;
import app.ideal.family.elbabatain.network.main.AdsImg_model;
import app.ideal.family.elbabatain.network.main.HeaderExample_Model;
import app.ideal.family.elbabatain.network.main.MainApi;

public class MainViewModel extends ViewModel {


    LiveData<List<HeaderExample_Model>> MHeaders;
    private static final String TAG = "MainViewModel";



    @Inject
    MainViewModel(MainApi mainApi , Application application , AppRepository appRepository) {

        appRepository.setAppRepository(application.getApplicationContext()
                , mainApi
                , null
                , null
                ,null
                ,null
                ,"MainActivity");

        appRepository.observeHeaders();
        MHeaders = appRepository.getAllHeaders();
        Log.e(TAG, "MainViewModel: MainViewModel Is Working...");
    }



}
