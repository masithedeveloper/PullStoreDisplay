package com.cat.facts.databinding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import com.cat.facts.data.remote.Resource;
import com.cat.facts.view.base.BaseAdapter;

import java.util.List;

final public class ListBindingAdapter{

    private ListBindingAdapter(){
    }

    @BindingAdapter(value = "resource")
    public static void setResource(RecyclerView recyclerView, Resource resource){
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if(adapter == null)
            return;

        if(resource == null || resource.data == null)
            return;

        if(adapter instanceof BaseAdapter){
            ((BaseAdapter)adapter).setData((List) resource.data);
        }
    }

}
