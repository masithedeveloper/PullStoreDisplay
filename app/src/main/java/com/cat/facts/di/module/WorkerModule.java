package com.cat.facts.di.module;


import androidx.work.Worker;

import com.cat.facts.data.remote.FetchFactsWorker;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class WorkerModule {

    @Binds
    abstract Worker bindsFetchFactsWorker(FetchFactsWorker fetchFactsWorker);
}
