package com.cat.facts.data.remote;

import android.content.Context;
import android.support.annotation.NonNull;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.cat.facts.data.remote.repository.FactRepository;


import javax.inject.Inject;

public class FetchFactsWorker extends Worker {

    private FactRepository factRepository;

    @Inject
    public FetchFactsWorker(@NonNull Context context, @NonNull WorkerParameters workerParams, FactRepository factRepository) {
        super(context, workerParams);
        this.factRepository = factRepository;
    }

    @NonNull
    @Override
    public Result doWork() {
        if (null != factRepository)
            return factRepository.workerLoadFacts();
        return null;
    }
}

