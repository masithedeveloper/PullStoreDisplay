package com.cat.facts.data.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.cat.facts.data.local.entity.FactEntity;

import java.util.List;

@Dao
public interface FactDao {
    @Query("SELECT * FROM fact")
    LiveData<List<FactEntity>> getFacts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveFacts(List<FactEntity> factEntities);
}
