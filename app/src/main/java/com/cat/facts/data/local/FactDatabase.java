package com.cat.facts.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.cat.facts.data.local.dao.FactDao;
import com.cat.facts.data.local.entity.FactEntity;

@Database(entities = {FactEntity.class}, version = 1)
public abstract class FactDatabase extends RoomDatabase {

    public abstract FactDao factDao();
}