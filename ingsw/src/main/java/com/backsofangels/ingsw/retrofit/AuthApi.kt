package com.backsofangels.ingsw.retrofit

import com.backsofangels.ingsw.model.User
import com.backsofangels.ingsw.model.UserDto
import com.backsofangels.ingsw.utils.RetrofitEndpoints
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.*


interface AuthApi {
    @POST(RetrofitEndpoints.AUTH_LOGIN)
    fun doLogin(
            @Header("Authorization") basicAuthorizationHeader: String?
    ): Observable<User>

    @POST(RetrofitEndpoints.AUTH_SIGNUP)
    fun doSignUp(
            @Body user: UserDto
    ): Observable<User>

    @GET(RetrofitEndpoints.AUTH_REFRESHTOKEN)
    fun refreshToken(): Observable<*>

    @GET(RetrofitEndpoints.AUTH_USERPROFILE)
    fun me(
            @Query("username") username: String
    ): Observable<User>
}