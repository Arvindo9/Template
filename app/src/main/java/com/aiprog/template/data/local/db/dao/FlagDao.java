package com.aiprog.template.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.aiprog.template.data.model.db.flag.Flag;

import java.util.List;

/**
 * Author       : Arvindo Mondal
 * Created on   : 25-12-2018
 * Email        : arvindomondal@gmail.com
 */
@Dao
public interface FlagDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Flag flag);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Flag> flagList);


    @Query("SELECT * FROM flag")
    List<Flag> loadAllFlag();

    //    @Query("Update flag set FlagBaseUrl = :flagBaseUrl where FlagBaseUrl = null")
    @Query("Update flag set FlagBaseUrl = :flagBaseUrl where FlagBaseUrl = null or FlagBaseUrl = \"null\"")
    void updateFlagBaseUrl(String flagBaseUrl);
}
