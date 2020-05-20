package com.cat.facts.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MenuItem;

import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.cat.facts.R;
import com.cat.facts.data.remote.FetchFactsWorker;
import com.cat.facts.databinding.ActivityMainBinding;
import com.cat.facts.utils.FragmentUtils;
import com.cat.facts.view.base.BaseActivity;
import com.cat.facts.view.fragment.FactListFragment;

import java.util.concurrent.TimeUnit;

import static com.cat.facts.utils.FragmentUtils.TRANSITION_NONE;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private static final String TAG = "FactsUpdate";

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentUtils.replaceFragment(this, FactListFragment.newInstance(), R.id.fragContainer, false, TRANSITION_NONE);

        PeriodicWorkRequest periodicWork = new PeriodicWorkRequest.Builder(FetchFactsWorker.class, 5, TimeUnit.MINUTES)
                .addTag(TAG)
                .build();

        WorkManager.getInstance().enqueueUniquePeriodicWork("Facts", ExistingPeriodicWorkPolicy.REPLACE, periodicWork);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }
}
