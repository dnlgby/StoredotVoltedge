package com.example.storedotvoltedge.di;

import com.example.storedotvoltedge.api.ApiService;
import com.example.storedotvoltedge.repository.Repository;
import com.example.storedotvoltedge.repository.TestRepository;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppModule extends AbstractModule {

    @Override
    protected void configure() {
        // Bind your interfaces to their implementations here
        bind(Repository.class).to(TestRepository.class);
    }

    @Provides
    public ApiService provideApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.publicapis.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiService.class);
    }
}
