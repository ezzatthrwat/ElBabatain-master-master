package app.ideal.family.elbabatain.database.main;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import app.ideal.family.elbabatain.database.DataConverter;
import app.ideal.family.elbabatain.network.contain.Contain_Model;
import app.ideal.family.elbabatain.network.events.Events_Model;
import app.ideal.family.elbabatain.network.main.AdsImg_model;
import app.ideal.family.elbabatain.network.main.HeaderExample_Model;
import app.ideal.family.elbabatain.network.news.News_Model;
import app.ideal.family.elbabatain.network.who_are_we.WhoAreWe_Model;


@Database(entities = {HeaderExample_Model.class , AdsImg_model.class , WhoAreWe_Model.class,
                      Contain_Model.class , News_Model.class , Events_Model.class},
                       version = 71 , exportSchema = false)
//@TypeConverters(DataConverter.class)
public abstract class MainDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "Header.db";
    private static volatile MainDatabase instance;
    private static final Object LOCK = new Object();

    public abstract AppDAO appDao();

    public static MainDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            MainDatabase.class, DATABASE_NAME).fallbackToDestructiveMigration().build();
                }
            }
        }
        return instance;
    }

}
