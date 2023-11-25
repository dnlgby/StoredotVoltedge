package com.example.storedotvoltedge;

import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

public interface ApiService {
    @GET("entries") // Replace with your actual endpoint
    Call<ApiResponse> getApiData();
}