package com.br.mimundi;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Miniatura.class}, version = 1, exportSchema = false)
public abstract class MiniaturaDatabase extends RoomDatabase {

    public abstract MiniaturaDAO miniaturaDAO();

    private static MiniaturaDatabase instance;

    public static MiniaturaDatabase getDatabase(final Context context){
        if ( instance == null ) {

            synchronized (MiniaturaDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context,
                            MiniaturaDatabase.class,
                            "miniatura.db")
                                .allowMainThreadQueries()
                                .build();
                }
            }
        }
        return instance;
    }
}
