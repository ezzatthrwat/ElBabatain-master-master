package app.ideal.family.elbabatain.ui.contain;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import app.ideal.family.elbabatain.AppRepository;
import app.ideal.family.elbabatain.network.contain.ContainApi;
import app.ideal.family.elbabatain.network.contain.Contain_Model;
import app.ideal.family.elbabatain.network.main.HeaderExample_Model;

public class ContainViewModel extends ViewModel {

    private static final String TAG = "ContainViewModel";

    LiveData<List<ContainViewModel>> MHeadersContains;


    private AppRepository appRepository ;

    @Inject
    ContainViewModel(ContainApi containApi , Application application ,AppRepository appRepository) {

        Log.e(TAG, "ContainViewModel:  Working....." );

      appRepository.setAppRepository(application.getApplicationContext()
                , null
                , containApi
                ,null
                , null
                , null
                ,"ContainActivity");

         this.appRepository = appRepository ;
    }

    LiveData<List<Contain_Model>> getAllContains(int id){

      return appRepository.getAllContains(id);
    }
}
