package com.backsofangels.ingsw.retrofit

import com.backsofangels.ingsw.utils.RetrofitEndpoints
import com.fasterxml.jackson.databind.ObjectMapper
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import kotlin.reflect.KClass

object RetrofitConfig {
    fun <T: Any> create(apiInterface: KClass<T>): T {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(StethoInterceptor())
                .build()

        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(RetrofitEndpoints.SERVER_BASE_URL)
                .build()
        return retrofit.create(apiInterface.java)
    }
}