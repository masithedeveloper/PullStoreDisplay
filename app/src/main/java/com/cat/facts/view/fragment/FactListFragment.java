package com.cat.facts.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cat.facts.R;
import com.cat.facts.data.remote.Status;
import com.cat.facts.databinding.FragmentListFactsBinding;
import com.cat.facts.view.adapter.FactsListAdapter;

import com.cat.facts.view.base.BaseFragment;
import com.cat.facts.viewmodel.FactListViewModel;

public class FactListFragment extends BaseFragment<FactListViewModel, FragmentListFactsBinding>{

    public static FactListFragment newInstance() {
        Bundle args = new Bundle();
        FactListFragment fragment = new FactListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Class<FactListViewModel> getViewModel() {
        return FactListViewModel.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_list_facts;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        setHasOptionsMenu(true);
        dataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        dataBinding.recyclerView.setAdapter(new FactsListAdapter());
        return dataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel.getFacts()
                .observe(this, listResource -> {
                    if(null != listResource && (listResource.status == Status.ERROR || listResource.status == Status.SUCCESS)){
                        dataBinding.progressBar.setVisibility(View.GONE);
                    }
                    dataBinding.setResource(listResource);

                    if(null != dataBinding.recyclerView.getAdapter() && dataBinding.recyclerView.getAdapter().getItemCount() > 0){
                        dataBinding.errorText.setVisibility(View.GONE);
                    }
                });

    }
}
