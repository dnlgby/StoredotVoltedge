package com.example.storedotvoltedge;

import com.google.inject.Inject;
import retrofit2.Callback;

import java.util.List;

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