package com.cat.facts.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.cat.facts.data.local.FactDatabase;
import com.cat.facts.data.local.dao.FactDao;
import com.cat.facts.data.remote.ApiConstants;
import com.cat.facts.data.remote.ApiService;
import com.cat.facts.data.remote.RequestInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = {ViewModelModule.class, WorkerModule.class})
public class AppModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(ApiConstants.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.readTimeout(ApiConstants.READ_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.writeTimeout(ApiConstants.WRITE_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.addInterceptor(new RequestInterceptor());
        okHttpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        return okHttpClient.build();
    }

    @Provides
    @Singleton
    ApiService provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(ApiService.class);
    }

    @Provides
    @Singleton
    FactDatabase provideFactDatabase(Application application) {
        return Room.databaseBuilder(application, FactDatabase.class, "facts.db").build();
    }

    @Provides
    @Singleton
    FactDao provideFactDao(FactDatabase factDatabase) {
        return factDatabase.factDao();
    }

}
