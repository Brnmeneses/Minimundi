package com.br.mimundi;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MiniaturaDAO {

    @Insert
    long insert(Miniatura miniatura);

    @Delete
    void delete(Miniatura miniatura);

    @Update
    void update(Miniatura miniatura);

    @Query("SELECT * FROM miniatura WHERE id = :id")
    Miniatura queryForId(long id);

    @Query("SELECT * FROM miniatura ORDER BY id")
    List<Miniatura> queryAll();

}
