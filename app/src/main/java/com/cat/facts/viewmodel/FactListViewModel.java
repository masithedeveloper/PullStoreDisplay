package com.cat.facts.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.cat.facts.data.local.entity.FactEntity;
import com.cat.facts.data.remote.Resource;
import com.cat.facts.data.remote.repository.FactRepository;

import java.util.List;

import javax.inject.Inject;

public class FactListViewModel extends ViewModel {

    private final LiveData<Resource<List<FactEntity>>> facts;

    @Inject
    public FactListViewModel(FactRepository factRepository) {
        facts = factRepository.loadFacts();
    }

    public LiveData<Resource<List<FactEntity>>> getFacts() {
        return facts;
    }
}
