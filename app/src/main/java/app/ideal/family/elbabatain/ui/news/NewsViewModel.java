package app.ideal.family.elbabatain.ui.news;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import app.ideal.family.elbabatain.AppRepository;
import app.ideal.family.elbabatain.network.news.NewsApi;
import app.ideal.family.elbabatain.network.news.News_Model;

public class NewsViewModel extends ViewModel {

    private static final String TAG = "NewsViewModel";

    private AppRepository appRepository ;
    @Inject
    NewsViewModel(Application application , NewsApi newsApi , AppRepository appRepository) {
        Log.e(TAG, "NewsViewModel: is Working....." );

        appRepository.setAppRepository(application
                , null
                , null
                , newsApi
                ,null
                ,null
                , "NewsActivity");

        this.appRepository = appRepository ;
    }

    LiveData<List<News_Model>> getAllNewsDataFromDatabase(int CurrentPage){

        return appRepository.getAllNewsDataFromDataBase(CurrentPage);
    }
}
