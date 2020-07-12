package com.backsofangels.ingsw.retrofit

import com.backsofangels.ingsw.utils.RetrofitEndpoints
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import kotlin.reflect.KClass

object RetrofitConfig {
    fun <T: Any> create(apiInterface: KClass<T>): T {
        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(RetrofitEndpoints.SERVER_BASE_URL)
                .build()
        return retrofit.create(apiInterface.java)
    }
}