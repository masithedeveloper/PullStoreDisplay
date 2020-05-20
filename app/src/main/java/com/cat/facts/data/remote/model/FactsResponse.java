package com.cat.facts.data.remote.model;

import com.google.gson.annotations.SerializedName;
import com.cat.facts.data.local.entity.FactEntity;

import java.util.List;

public class FactsResponse {

    @SerializedName("all")
    private List<FactEntity> factsResponse;

    public List<FactEntity> getFactsResponse() {
        return factsResponse;
    }

    public void setFactsResponse(List<FactEntity> factsResponse) {
        this.factsResponse = factsResponse;
    }
}
