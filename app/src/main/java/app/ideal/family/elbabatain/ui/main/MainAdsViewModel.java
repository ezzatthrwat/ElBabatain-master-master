package app.ideal.family.elbabatain.ui.main;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Insert;

import java.util.List;

import javax.inject.Inject;

import app.ideal.family.elbabatain.AppRepository;
import app.ideal.family.elbabatain.network.main.AdsImg_model;
import app.ideal.family.elbabatain.network.main.MainApi;

public class MainAdsViewModel extends ViewModel {

    LiveData<List<AdsImg_model>> adsImg_models ;

    @Inject
    MainAdsViewModel(MainApi mainApi, Application application, AppRepository appRepository) {

        appRepository.setAppRepository(application.getApplicationContext()
                , mainApi
                , null
                , null
                ,null
                ,null
                ,"MainActivity");

        appRepository.getImageAdsFromApi();
        adsImg_models = appRepository.getAdsImgFromDatabase();

    }
}
