package com.example.storedotvoltedge.repository;

import com.example.storedotvoltedge.models.ApiResponse;
import com.example.storedotvoltedge.api.ApiService;
import com.google.inject.Inject;
import retrofit2.Callback;

public class TestRepository implements Repository{

    private final ApiService apiService;

    @Inject
    public TestRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public void getStringList(Callback<ApiResponse> callback) {
        apiService.getApiData().enqueue(callback);
    }
}