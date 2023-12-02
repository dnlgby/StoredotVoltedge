package com.example.storedotvoltedge.viewmodel;

import com.example.storedotvoltedge.models.ApiEntry;
import com.example.storedotvoltedge.models.ApiResponse;
import com.example.storedotvoltedge.repository.TestRepository;
import com.google.inject.Inject;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableObjectValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;

public class MainViewModel {

    private final TestRepository repository;

    // Wrapper can be set, its initial to this class.
    private final ReadOnlyListWrapper<ApiEntry> data = new ReadOnlyListWrapper<>(FXCollections.observableArrayList());

    // The property is read-only, so it can be exposed to the MainApp class.
    private final ReadOnlyListProperty<ApiEntry> dataProperty = data.getReadOnlyProperty();

    // Error property is a simple object property that holds errors, so it can be exposed to the MainApp class.
    private final SimpleObjectProperty<Throwable> errorProperty = new SimpleObjectProperty<>(null);

    @Inject
    public MainViewModel(TestRepository repository) {
        this.repository = repository;
    }

    public ReadOnlyListProperty<ApiEntry> getDataProperty() {
        return dataProperty;
    }

    public ObservableObjectValue<Throwable> errorProperty() {
        return errorProperty;
    }

    public void loadData() {
        repository.getStringList(new Callback<>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse apiResponse = response.body();
                    List<ApiEntry> entries = apiResponse.getEntries();
                    data.setAll(entries);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                errorProperty.set(t);
            }
        });
    }
}