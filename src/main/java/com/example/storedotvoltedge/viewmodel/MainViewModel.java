package com.example.storedotvoltedge.viewmodel;

import com.example.storedotvoltedge.models.Action;
import com.example.storedotvoltedge.models.ApiResponse;
import com.example.storedotvoltedge.repository.TestRepository;
import com.google.inject.Inject;
import javafx.beans.property.*;
import javafx.beans.value.ObservableObjectValue;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel {

    private final TestRepository repository;

    private final ReadOnlyObjectWrapper<Action<?>> actionPropertyWrapper = new ReadOnlyObjectWrapper<>();

    public ReadOnlyObjectProperty<Action<?>> actionProperty() {
        return actionPropertyWrapper.getReadOnlyProperty();
    }
//
//    // Wrapper can be set, its initial to this class.
//    private final ReadOnlyListWrapper<ApiEntry> data = new ReadOnlyListWrapper<>(FXCollections.observableArrayList());
//    ReadOnlyStringWrapper stringWrapper = new ReadOnlyStringWrapper();
//    // The property is read-only, so it can be exposed to the MainApp class.
//    private final ReadOnlyListProperty<ApiEntry> dataProperty = data.getReadOnlyProperty();

    // Error property is a simple object property that holds errors, so it can be exposed to the MainApp class.
    private final SimpleObjectProperty<Throwable> errorProperty = new SimpleObjectProperty<>(null);

    @Inject
    public MainViewModel(TestRepository repository) {
        this.repository = repository;
    }

//    public ReadOnlyListProperty<ApiEntry> getDataProperty() {
//        return dataProperty;
//    }

    public ObservableObjectValue<Throwable> errorProperty() {
        return errorProperty;
    }

    public void loadApiEntryData() {
        repository.getStringList(new Callback<>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    System.out.println("Successfully loaded data");
                    ApiResponse apiResponse = response.body();
                    actionPropertyWrapper.setValue(Action.actionSuccess(apiResponse.getEntries()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                System.out.println("Failed to load data");
                actionPropertyWrapper.setValue(Action.actionFailure(t.getMessage()));
                errorProperty.set(t);
            }
        });
    }
}