package com.divar.network.di

import android.util.Log
import com.divar.network.BuildConfig
import com.divar.network.preferences.TokenPreferences
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
    }

    @Singleton
    @Provides
    fun provideOkhttpClient(tokenPreferences: TokenPreferences): OkHttpClient.Builder {
        val http = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)

        http.addInterceptor(Interceptor { chain: Interceptor.Chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header("Content-Type", "application/json")
                .header("Authorization", tokenPreferences.readToken())
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        }).build()

        val logInterceptor = HttpLoggingInterceptor(logger = {
            if (BuildConfig.DEBUG)
                Log.d("Retrofit", it)
        })
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        http.addInterceptor(logInterceptor)
        return http
    }


    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient.Builder, json: Json): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.69.157:8080/api/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(client.build())
            .build()
    }

}