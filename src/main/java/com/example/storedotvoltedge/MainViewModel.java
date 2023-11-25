package com.example.storedotvoltedge;

import com.google.inject.Inject;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;

public class MainViewModel {

    private final TestRepository repository;
    private final ObservableList<ApiEntry> data = FXCollections.observableArrayList();

    @Inject
    public MainViewModel(TestRepository repository) {
        this.repository = repository;
    }

    public ObservableList<ApiEntry> getData() {
        return data;
    }

    public void loadData() {
        repository.getStringList(new Callback<>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse apiResponse = response.body();
                    List<ApiEntry> entries = apiResponse.getEntries();
                    data.addAll(entries);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                // Handle failure...
            }
        });
    }
}