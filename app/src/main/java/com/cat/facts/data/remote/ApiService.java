package com.cat.facts.data.remote;

import com.cat.facts.data.remote.model.FactsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("facts")
    Call<FactsResponse> getFacts();
}
