package com.cat.facts.view.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cat.facts.data.local.entity.FactEntity;
import com.cat.facts.databinding.ItemFactListBinding;
import com.cat.facts.view.base.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class FactsListAdapter extends  BaseAdapter<FactsListAdapter.FactsViewHolder, FactEntity> {

    private List<FactEntity> factEntities;

    public FactsListAdapter() {
        factEntities = new ArrayList<>();
    }

    @Override
    public void setData(List<FactEntity> entities) {
        this.factEntities = entities;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FactsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return FactsViewHolder.create(LayoutInflater.from(viewGroup.getContext()), viewGroup);
    }

    @Override
    public void onBindViewHolder(@NonNull FactsViewHolder viewHolder, int i) {
        viewHolder.onBind(factEntities.get(i));
    }

    @Override
    public int getItemCount() {
        return factEntities.size();
    }

    static class FactsViewHolder extends RecyclerView.ViewHolder {

        private static FactsViewHolder create(LayoutInflater inflater, ViewGroup parent) {
            ItemFactListBinding itemFactListBinding = ItemFactListBinding.inflate(inflater, parent, false);
            return new FactsViewHolder(itemFactListBinding);
        }

        final ItemFactListBinding binding;

        private FactsViewHolder(ItemFactListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void onBind(FactEntity factEntity) {
            binding.setFact(factEntity);
            binding.executePendingBindings();
        }
    }
}
