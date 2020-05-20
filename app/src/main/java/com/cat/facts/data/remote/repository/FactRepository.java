package com.cat.facts.data.remote.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import androidx.work.ListenableWorker;

import com.cat.facts.data.local.dao.FactDao;
import com.cat.facts.data.local.entity.FactEntity;
import com.cat.facts.data.remote.ApiService;
import com.cat.facts.data.remote.NetworkBoundResource;
import com.cat.facts.data.remote.Resource;
import com.cat.facts.data.remote.model.FactsResponse;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class FactRepository {

    private final FactDao factDao;
    private final ApiService apiService;

    @Inject
    FactRepository(FactDao dao, ApiService service) {
        this.factDao = dao;
        this.apiService = service;
    }

    public LiveData<Resource<List<FactEntity>>> loadFacts() {
        return new NetworkBoundResource<List<FactEntity>, FactsResponse>() {

            @Override
            protected void saveCallResult(FactsResponse item) {
                if(null != item)
                    factDao.saveFacts(item.getFactsResponse());
            }

            @NonNull
            @Override
            protected LiveData<List<FactEntity>> loadFromDb() {
                return factDao.getFacts();
            }

            @NonNull
            @Override
            protected Call<FactsResponse> createCall() {
                return apiService.getFacts();
            }
        }.getAsLiveData();
    }

    public ListenableWorker.Result workerLoadFacts() {
        new NetworkBoundResource<List<FactEntity>, FactsResponse>() {
            @Override
            protected void saveCallResult(FactsResponse item) {
                if(null != item)
                    factDao.saveFacts(item.getFactsResponse());
            }

            @NonNull
            @Override
            protected LiveData<List<FactEntity>> loadFromDb() {
                return factDao.getFacts();
            }

            @NonNull
            @Override
            protected Call<FactsResponse> createCall() {
                return apiService.getFacts();
            }
        }.getAsLiveData();

        return null;
    }
}

