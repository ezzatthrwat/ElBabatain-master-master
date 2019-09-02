package app.ideal.family.elbabatain;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.ideal.family.elbabatain.database.main.MainDatabase;
import app.ideal.family.elbabatain.network.contain.ContainApi;
import app.ideal.family.elbabatain.network.contain.Contain_Model;
import app.ideal.family.elbabatain.network.events.EventsApi;
import app.ideal.family.elbabatain.network.events.Events_Model;
import app.ideal.family.elbabatain.network.main.AdsImg_model;
import app.ideal.family.elbabatain.network.main.HeaderExample_Model;
import app.ideal.family.elbabatain.network.main.MainApi;
import app.ideal.family.elbabatain.network.news.NewsApi;
import app.ideal.family.elbabatain.network.news.News_Model;
import app.ideal.family.elbabatain.network.news.Request_Paging;
import app.ideal.family.elbabatain.network.who_are_we.WhoAreWe_Api;
import app.ideal.family.elbabatain.network.who_are_we.WhoAreWe_Model;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class AppRepository {

    private static final String TAG = "AppRepository";

    private MainDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();

    private MainApi mainApi;
    private ContainApi containApi;
    private NewsApi newsApi;
    private EventsApi eventsApi ;
    private WhoAreWe_Api whoAreWe_api ;


//    public static AppRepository getInstance(Context context , MainApi mainApi , ContainApi containApi, String ActivityName) {
//
//        // only one Instance can be take
//        if (ourInstance == null) {
//            ourInstance = new AppRepository(context , mainApi ,containApi ,  ActivityName);
//        }
//        return ourInstance;
//    }


    @Inject
    public AppRepository() {
    }

    public void setAppRepository(Context context,
                                 MainApi mainApi,
                                 ContainApi containApi,
                                 NewsApi newsApi ,
                                 EventsApi eventsApi ,
                                 WhoAreWe_Api whoAreWeApi ,
                                 String ActivityName) {

        switch (ActivityName) {
            case "MainActivity":
                this.mainApi = mainApi;
                break;
            case "ContainActivity":
                this.containApi = containApi;
                break;
            case "NewsActivity":
                this.newsApi = newsApi;
                break;
            case "EventActivity":
                this.eventsApi = eventsApi;
                break;
            case "WhoAreWe":
                this.whoAreWe_api = whoAreWeApi ;
                break;
        }
        this.mDb = MainDatabase.getInstance(context);
    }


//---------------------------------------------- MainActivity Get Header Data ---------------------------------------------

    public void observeHeaders() {

        mainApi.getUserPost().enqueue(new Callback<List<HeaderExample_Model>>() {
            @Override
            public void onResponse(@NonNull Call<List<HeaderExample_Model>> call, @NonNull Response<List<HeaderExample_Model>> response)
            {


                if (response.isSuccessful()) {
//                    Log.e(TAG, "onResponse: Api is Working....");
                    if (response.body() != null) {
//                        Collections.reverse(headersEntities);
                        DeleteAllHeadersToInsertNew(response.body());
                        List<HeaderExample_Model>  headerExample_models  = response.body();

                        insertOrUpdateHeaders(response.body());

                        List<HeaderExample_Model> example_modelList = new ArrayList<>();

                        HeaderExample_Model model8 = new HeaderExample_Model();
                        model8.setTitle("WhatsApp");
                        model8.setImgUrl("http://familyalbabtain.com/img_mobile/other/whatsapp.png");
                        example_modelList.add(model8);

                        HeaderExample_Model model9 = new HeaderExample_Model();
                        model9.setTitle("Snapchat");
                        model9.setImgUrl("http://familyalbabtain.com/img_mobile/other/ic_snap.png");
                        example_modelList.add(model9);
//
                        HeaderExample_Model model5 = new HeaderExample_Model();
                        model5.setTitle("YouTube");
                        model5.setImgUrl("http://www.familyalbabtain.com/img/other/youtube.png");
                        example_modelList.add(model5);

                        HeaderExample_Model model6 = new HeaderExample_Model();
                        model6.setTitle("Twitter");
                        model6.setImgUrl("http://www.familyalbabtain.com/img/other/twitter.png");
                        example_modelList.add(model6);

                        HeaderExample_Model model7 = new HeaderExample_Model();
                        model7.setTitle("Instagram");
                        model7.setImgUrl("http://www.familyalbabtain.com/img/other/instagram.png");
                        example_modelList.add(model7);

                        HeaderExample_Model model1 = new HeaderExample_Model();
                        model1.setTitle("اخبار الاسرة");
                        model1.setImgUrl("http://www.familyalbabtain.com/img_mobile/headers/news.png");
                        example_modelList.add(model1);

                        HeaderExample_Model model2 = new HeaderExample_Model();
                        model2.setTitle("تقويم المناسبات");
                        model2.setImgUrl("http://www.familyalbabtain.com/img_mobile/headers/events.png");
                        example_modelList.add(model2);

                        HeaderExample_Model model3 = new HeaderExample_Model();
                        model3.setTitle("من نحن");
                        model3.setImgUrl("http://www.familyalbabtain.com/img_mobile/headers/persons.png");
                        example_modelList.add(model3);

                        HeaderExample_Model model4 = new HeaderExample_Model();
                        model4.setTitle("تواصل معنا");
                        model4.setImgUrl("http://www.familyalbabtain.com/img_mobile/headers/email-icon.png");
                        example_modelList.add(model4);

//                          example_modelList.addAll(response.body());

                        insertOrUpdateAllConstantHeaders(example_modelList);
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<List<HeaderExample_Model>> call, @NonNull Throwable t) {

                Log.e(TAG, "onFailure: " +t.getMessage() );
            }
        });
    }

    private void insertOrUpdateHeaders(final List<HeaderExample_Model> headers) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < headers.size(); i++) {

                    HeaderExample_Model itemFromDB = mDb.appDao().getHeaderItemById(headers.get(i).getId());

                    if (itemFromDB == null) {
                        mDb.appDao().insertAllHeader(headers);
                    } else {
                        mDb.appDao().updateQuantity(headers.get(i));
                    }

                }
            }
        });

    }

    private void insertOrUpdateAllConstantHeaders(final List<HeaderExample_Model> headers) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < headers.size(); i++) {

                    HeaderExample_Model itemFromDB = mDb.appDao().getItemByName(headers.get(i).getTitle());

                    if (itemFromDB == null) {
                        mDb.appDao().insertAllHeader(headers);
                    } else {
                        mDb.appDao().updateQuantity(headers.get(i));
                    }

                }
            }
        });


    }

    public LiveData<List<HeaderExample_Model>> getAllHeaders() {
        return mDb.appDao().getAll();
    }

    private void DeleteAllHeadersToInsertNew(List<HeaderExample_Model> headers){
        executor.execute(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < headers.size(); i++) {

                   HeaderExample_Model itemFromDB = mDb.appDao().getItemByName(headers.get(i).getTitle());

                   if (itemFromDB != null){
                       mDb.appDao().deleteAllHeadersToInsertNew();
                   }

                }

            }
        });
    }

    public void getImageAdsFromApi(){

        mainApi.getAdsImg().enqueue(new Callback<List<AdsImg_model>>() {
            @Override
            public void onResponse(Call<List<AdsImg_model>> call, Response<List<AdsImg_model>> response) {

                if (response.isSuccessful()){
                    if (response.body() != null){
                        InsertOrUpdateAdsImage(response.body());
                        Log.e(TAG, "Ads_Img onResponse: Working....... ");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<AdsImg_model>> call, Throwable t) {

                Log.e(TAG, "Ads_Img onFailure: " + t.getMessage() );
            }
        });

    }

    private void InsertOrUpdateAdsImage(List<AdsImg_model> adsImg_models){

        executor.execute(new Runnable() {
            @Override
            public void run() {

                mDb.appDao().deleteAllAdsImgToInsertNew();

                for (int i = 0; i < adsImg_models.size(); i++) {

                    AdsImg_model itemFromDB = mDb.appDao().getAdsImgItemById(adsImg_models.get(i).getId());

                    if (itemFromDB == null) {
                        mDb.appDao().InsertAdsImgToDatabase(adsImg_models);
                    } else {
                        mDb.appDao().UpdateAdsImgIfExist(adsImg_models.get(i));
                    }

                }
            }
        });
    }


    public LiveData<List<AdsImg_model>> getAdsImgFromDatabase(){


        return mDb.appDao().getAllAdsImgFromDatabase();
    }

//---------------------------------------------- ContainActivity Get Header Contain Data ----------------------------------

    public LiveData<List<Contain_Model>> getAllContains(int id) {

        observeContain(id);

        return mDb.appDao().getAllHeadersContains(id);
    }

    private void observeContain(int id) {

        containApi.getHeaderContain(id).enqueue(new Callback<List<Contain_Model>>() {
            @Override
            public void onResponse(@NonNull Call<List<Contain_Model>> call, @NonNull Response<List<Contain_Model>> response) {

                if (response.isSuccessful()) {
                    if (response.body()!= null){

                        executor.execute(new Runnable() {
                            @Override
                            public void run() {
//
                                mDb.appDao().deleteItemsThatRemovedFromControllPanell(String.valueOf(id));

                                insertOrUpdateHeadersContains(response.body());

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Contain_Model>> call, @NonNull Throwable t) {

            }
        });



    }

    private void insertOrUpdateHeadersContains(final List<Contain_Model> contain_models ) {


                    for (int i = 0; i < contain_models.size(); i++) {

                        Contain_Model itemFromDB = mDb.appDao().getContainItemById(contain_models.get(i).getId());

                        if (itemFromDB == null) {

                            mDb.appDao().insertAllHeaderContains(contain_models);
                        } else {

                            mDb.appDao().updateHeaderContains(contain_models.get(i));
                        }
                    }
    }


//---------------------------------------------- NewsActivity Get Data ----------------------------------------------------

    private void getAllNewsFromApi(int CurrentPage) {

        newsApi.getNews(CurrentPage).enqueue(new Callback<Request_Paging>() {
            @Override
            public void onResponse(@NonNull Call<Request_Paging> call, @NonNull Response<Request_Paging> response) {

                if (response.isSuccessful()) {
//                    Log.e(TAG, "onResponse: NewsApi is Working.....");

                    if (response.body() != null) {

                        deleteAllNewsToInsertNew();
                        insertOrUpdateNewsIntoDatabase(response.body().getData());
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<Request_Paging> call, @NonNull Throwable t) {

            }
        });
     }


     private void insertOrUpdateNewsIntoDatabase(final List<News_Model> news_models){

        executor.execute(new Runnable() {
            @Override
            public void run() {

                for (int i = 0 ; i < news_models.size() ; i++){

                    News_Model itemFromDB = mDb.appDao().getNewsItemById(news_models.get(i).getId());

                    if (itemFromDB == null){
                        mDb.appDao().insertAllNewsToDatabase(news_models);
                    }else{
                        mDb.appDao().updateNews(news_models.get(i));
                    }

                }
            }
        });
     }


     public LiveData<List<News_Model>> getAllNewsDataFromDataBase(int CurrentPage){

         getAllNewsFromApi(CurrentPage);

         return mDb.appDao().getAllNewsFromDatabase();
     }

     private void deleteAllNewsToInsertNew(){

        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.appDao().deleteAllNewsToInsertNew();
            }
        });
     }

//---------------------------------------------- EventsActivity Get Data ---------------------------------------------------

    private void getAllEventsFromApi(){
        eventsApi.getAllEventFromApi().enqueue(new Callback<List<Events_Model>>() {
            @Override
            public void onResponse(@NonNull Call<List<Events_Model>> call, @NonNull Response<List<Events_Model>> response) {

                if (response.isSuccessful()){
                    Log.e(TAG, "onResponse: AllEventApi is Working....." );

                    if (response.body() != null){
                        deleteAllEventsToInsertNew();
                        InsertOrUpdateAllEventsFromDatabase(response.body());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Events_Model>> call, @NonNull Throwable t) {
                Log.e(TAG, "onResponse: AllEventApi " + t.getMessage() );

            }
        });
    }

    public void getAllEventsByDateFromApi(String Date){
        eventsApi.getAllEventsByDateFromApi(Date).enqueue(new Callback<List<Events_Model>>() {
            @Override
            public void onResponse(@NonNull Call<List<Events_Model>> call, @NonNull Response<List<Events_Model>> response) {

                if (response.isSuccessful()){
                    Log.e(TAG, "onResponse: AllEventsByDateApi is Working...." );
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Events_Model>> call, @NonNull Throwable t) {

                Log.e(TAG, "onFailure: AllEventByDate Api" + t.getMessage() );
            }
        });
    }

    private void InsertOrUpdateAllEventsFromDatabase(List<Events_Model> eventsModelList){

        executor.execute(new Runnable() {
            @Override
            public void run() {

                for (int i= 0 ; i<eventsModelList.size() ; i++){
                    Events_Model itemFromDB = mDb.appDao().getEventBtyID(eventsModelList.get(i).getId());

                    if (itemFromDB == null){

                        mDb.appDao().insertAllEventsIntoDatabase(eventsModelList);
                    }else{
                        mDb.appDao().updateEvent(eventsModelList.get(i));
                    }
                }
            }
        });
    }

    public LiveData<List<Events_Model>> getAllEventsFromDatabase(){
        getAllEventsFromApi();

        return mDb.appDao().getALLEventsFromDatabase();
    }

    public LiveData<List<Events_Model>> getAllEventsFromDatabaseByDate(String Date){

        return mDb.appDao().getAllEventsByDateFromDatabase(Date);
    }

    private void deleteAllEventsToInsertNew(){

        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.appDao().deleteAllEventToInsertNew();
            }
        });
    }


    //---------------------------------------------- WhoAreWE Get Data  ----------------------------------

    private void getWhoAreWeDataFromApi(){
        whoAreWe_api.getWhoAreWeRemoteData().enqueue(new Callback<WhoAreWe_Model>() {
            @Override
            public void onResponse(Call<WhoAreWe_Model> call, Response<WhoAreWe_Model> response) {

                if (response.isSuccessful()){
                    if (response.body() != null){

                        insertOrUpdateWhoAreWeData(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<WhoAreWe_Model> call, Throwable t) {

            }
        });
    }

    private void insertOrUpdateWhoAreWeData(WhoAreWe_Model whoAreWe_model) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
//                mDb.appDao().deleteWhoAreWeDataToInsertNew();

                WhoAreWe_Model itemFromDB = mDb.appDao().getWhoAreWeItemByID(whoAreWe_model.getId());

                if (itemFromDB == null) {

                    mDb.appDao().insertWhoAreWeDataIntoDatabase(whoAreWe_model);
                } else {
                    mDb.appDao().updateWhoAreWe(whoAreWe_model);
                }
            }

        });
    }


    private void DeleteWhoAreWeDataToInsertNew(){

        executor.execute(new Runnable() {
            @Override
            public void run() {

            }
        });
    }

    public LiveData<WhoAreWe_Model> getWhoAreWeDataFromDatabase(){

        getWhoAreWeDataFromApi();

        return mDb.appDao().getWhoAreWeDataFromDatabase() ;
    }



}


