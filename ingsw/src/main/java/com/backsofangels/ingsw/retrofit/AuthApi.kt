package com.backsofangels.ingsw.retrofit

import com.backsofangels.ingsw.model.User
import com.backsofangels.ingsw.utils.RetrofitEndpoints
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface AuthApi {
    @POST(RetrofitEndpoints.AUTH_LOGIN)
    fun doLogin(
            @Header("Authorization") basicAuthorizationHeader: String?
    ): Observable<User?>?

    @POST(RetrofitEndpoints.AUTH_SIGNUP)
    fun doSignUp(
            @Body user: User?
    ): Observable<*>?

    @GET(RetrofitEndpoints.AUTH_REFRESHTOKEN)
    fun refreshToken(): Observable<*>?
}