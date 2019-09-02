package app.ideal.family.elbabatain.database.main;

import androidx.annotation.RequiresPermission;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.TypeConverter;
import androidx.room.Update;

import java.util.List;

import app.ideal.family.elbabatain.di.news.News_Module;
import app.ideal.family.elbabatain.network.contain.Contain_Model;
import app.ideal.family.elbabatain.network.events.Events_Model;
import app.ideal.family.elbabatain.network.main.AdsImg_model;
import app.ideal.family.elbabatain.network.main.HeaderExample_Model;
import app.ideal.family.elbabatain.network.news.News_Model;
import app.ideal.family.elbabatain.network.who_are_we.WhoAreWe_Model;

@Dao
public interface AppDAO {

    //---------------------------------------------- MainActivity Get Header Data ---------------------------------------------

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllHeader(List<HeaderExample_Model> HeaderExample_Model);

    @Query("SELECT * FROM HEADER ORDER BY customOrder DESC")
    LiveData<List<HeaderExample_Model>> getAll();

    @Query("SELECT * from header WHERE id= :id ")
    HeaderExample_Model getHeaderItemById(int id) ;

    @Query("SELECT * from header WHERE title= :title")
    HeaderExample_Model getItemByName(String title) ;

    @Update
    void updateQuantity(HeaderExample_Model headersEntities);

    @Query("DELETE FROM header")
    void deleteAllHeadersToInsertNew();

                                     //----------AdsImg-----------//

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void  InsertAdsImgToDatabase(List<AdsImg_model> adsImg_models);

    @Update()
    void UpdateAdsImgIfExist(AdsImg_model adsImg_model);

    @Query("SELECT * FROM ads_img")
    LiveData<List<AdsImg_model>> getAllAdsImgFromDatabase();

    @Query("SELECT * from ads_img WHERE id= :id")
    AdsImg_model getAdsImgItemById(int id) ;

    @Query("DELETE FROM ads_img")
    void deleteAllAdsImgToInsertNew();


//---------------------------------------------- ContainActivity Get Header Contain Data ----------------------------------

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllHeaderContains(List<Contain_Model> contain_models) ;

    @Query("SELECT * FROM header_contain WHERE headerId = :id")
    LiveData<List<Contain_Model>> getAllHeadersContains( int id) ;

    @Query("SELECT * from header_contain WHERE id= :id")
    Contain_Model getContainItemById(int id) ;

    @Update
    void updateHeaderContains(Contain_Model contain_model);

    @Query("DELETE FROM header_contain ")
    void deleteAllHeadersContainToInsertNew();

    @Query("DELETE FROM header_contain WHERE headerId= :id")
    void deleteItemsThatRemovedFromControllPanell(String id);


//---------------------------------------------- NewsActivity Get Data ----------------------------------------------------


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllNewsToDatabase(List<News_Model> news_models) ;

    @Query("SELECT * FROM news ")
    LiveData<List<News_Model>> getAllNewsFromDatabase();

    @Query("SELECT * FROM news WHERE id= :id")
    News_Model getNewsItemById(int id);

    @Update
    void updateNews(News_Model news_model);

    @Query("DELETE FROM news")
    void deleteAllNewsToInsertNew();

    //------------------------------------------ EventActivity Get Data -----------------------------------------------------


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllEventsIntoDatabase(List<Events_Model> events_models) ;

    @Query("SELECT * FROM events")
    LiveData<List<Events_Model>> getALLEventsFromDatabase();

    @Query("SELECT * FROM events WHERE startDate = :date")
    LiveData<List<Events_Model>> getAllEventsByDateFromDatabase(String date);

    @Query("SELECT * FROM events WHERE id = :id")
    Events_Model getEventBtyID(int id);

    @Update()
    void updateEvent(Events_Model events_model) ;

    @Query("DELETE FROM events")
    void deleteAllEventToInsertNew();


    //---------------------------------------------- WhoAreWE Get Data From Database ----------------------------------

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWhoAreWeDataIntoDatabase(WhoAreWe_Model whoAreWe_model);

    @Query("SELECT * FROM WHO_ARE_WE")
    LiveData<WhoAreWe_Model> getWhoAreWeDataFromDatabase();

    @Update()
    void updateWhoAreWe(WhoAreWe_Model WhoAreWe_Model) ;


    @Query("SELECT * FROM who_are_we WHERE id = :id")
    WhoAreWe_Model getWhoAreWeItemByID(int id);


    @Query("DELETE FROM who_are_we")
    void deleteWhoAreWeDataToInsertNew();


}


