package app.ideal.family.elbabatain.ui.whoarewe;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import app.ideal.family.elbabatain.AppRepository;
import app.ideal.family.elbabatain.network.who_are_we.WhoAreWe_Api;
import app.ideal.family.elbabatain.network.who_are_we.WhoAreWe_Model;

public class WhoAreWe_ViewModel extends ViewModel {

    LiveData<WhoAreWe_Model> whoAreWe_modelLiveData ;

    @Inject
    WhoAreWe_ViewModel(Application application, WhoAreWe_Api whoAreWe_api, AppRepository appRepository) {

        appRepository.setAppRepository(application
                , null
                , null
                , null
                ,null
                ,whoAreWe_api
                , "WhoAreWe");

        whoAreWe_modelLiveData =  appRepository.getWhoAreWeDataFromDatabase();
    }
}
