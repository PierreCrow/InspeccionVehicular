package com.futuromovil.inspeccionvehicular.data.datasource.db.store.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.futuromovil.inspeccionvehicular.data.datasource.db.store.model.DbTabla;

import java.util.List;

@Dao
public interface TablaDao {

    @Insert
    void InsertOnlyOne(DbTabla dbUsuario);

    @Insert
    void InsertMultiple(List<DbTabla> dbUsuarios);

    @Update
    void Update(DbTabla dbUsuario);

    @Query("SELECT * FROM DbUsuario")
    List<DbTabla> GetAll();

    @Delete
    void Delete(DbTabla dbUsuario);

    @Query("DELETE  FROM DbUsuario")
    void DeleteAll();
}
