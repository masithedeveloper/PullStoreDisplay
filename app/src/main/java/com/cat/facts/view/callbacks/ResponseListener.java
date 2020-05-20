package com.cat.facts.view.callbacks;

import com.cat.facts.data.local.entity.FactEntity;

public interface ResponseListener {

    void onSuccess(FactEntity data);
    void onFailure(String message);
}
