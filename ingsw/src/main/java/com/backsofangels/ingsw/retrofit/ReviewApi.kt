package com.backsofangels.ingsw.retrofit

import com.backsofangels.ingsw.model.Review
import com.backsofangels.ingsw.model.Structure
import com.backsofangels.ingsw.model.User
import com.backsofangels.ingsw.utils.RetrofitEndpoints
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ReviewApi {
    @GET(RetrofitEndpoints.REVIEW_GET_FOR_STRUCTURE)
    fun getReviewsForStructure(
            @Query("structureId") structureId: Int?,
            @Query("structure") structure: Structure?,
            @Query("limit") limit: Int?,
            @Query("page") page: Int?
    ): Observable<Array<Review>>

    @GET(RetrofitEndpoints.REVIEW_GET_FOR_USER)
    fun getReviewsForUser(
            @Query("user") user: User?,
            @Query("limit") limit: Int?,
            @Query("page") page: Int?
    ): Observable<Array<Review>>

    @POST(RetrofitEndpoints.REVIEW_POST)
    fun postNewReview(
            @Body review: Review?
    ): Observable<Review?>?

    @POST(RetrofitEndpoints.REVIEW_UPDATE)
    fun updateReview(
            @Body oldReview: Review?,
            @Body newReview: Review?
    ): Observable<Review?>?
}