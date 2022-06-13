package com.futuromovil.inspeccionvehicular.data.datasource.db.store;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.futuromovil.inspeccionvehicular.data.datasource.db.store.dao.TablaDao;
import com.futuromovil.inspeccionvehicular.data.datasource.db.store.model.DbTabla;
import com.futuromovil.inspeccionvehicular.presentation.utils.Converters;

@Database(entities = {
        DbTabla.class,
},
        version = 3, exportSchema = false)
@TypeConverters({Converters.class})

public abstract class InspeccionVehicularDB extends RoomDatabase {

    public static int versionDb = 5;

    private static InspeccionVehicularDB INSTANCE;
    public static final String DB_NAME = "inspecciones.db";
    private static Context mContext;


    public static InspeccionVehicularDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (InspeccionVehicularDB.class) {
                if (INSTANCE == null) {
                    mContext = context.getApplicationContext();
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            InspeccionVehicularDB.class, DB_NAME)
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries() // SHOULD NOT BE USED IN PRODUCTION !!!
                            .addCallback(new RoomDatabase.Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    // Log.i(TAG, "populating with data...");
//                                    new PopulateDbAsync(INSTANCE).execute();
                                }
                            }).build();
                }
            }
        }
        return INSTANCE;
    }


    public abstract TablaDao userDao();


}

