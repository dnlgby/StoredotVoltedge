package com.example.storedotvoltedge.api;

import com.example.storedotvoltedge.models.ApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("entries") // Replace with your actual endpoint
    Call<ApiResponse> getApiData();
}