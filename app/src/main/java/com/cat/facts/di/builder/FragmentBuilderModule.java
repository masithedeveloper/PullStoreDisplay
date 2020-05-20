package com.cat.facts.di.builder;

import com.cat.facts.view.fragment.FactListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract FactListFragment contributeFactListFragment();
}
