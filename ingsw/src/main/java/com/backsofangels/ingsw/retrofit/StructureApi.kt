package com.backsofangels.ingsw.retrofit

import com.backsofangels.ingsw.model.Hotel
import com.backsofangels.ingsw.model.Restaurant
import com.backsofangels.ingsw.model.Structure
import com.backsofangels.ingsw.utils.RetrofitEndpoints
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface StructureApi {
    @GET(RetrofitEndpoints.STRUCTURE_SEARCH_URL)
    fun getAllStructures(
            @Query("limit") limit: Int,
            @Query("page") pageNumber: Int
    ): Observable<Array<Structure>>

    @GET(RetrofitEndpoints.RESTAURANT_SEARCH_URL)
    fun getRestaurants(
            @Query("name") name: String,
            @Query("nation") nation: String,
            @Query("town") town: String,
            @Query("priceRange") priceRange: Int,
            @Query("limit") limit: Int,
            @Query("page") pageNumber: Int
    ): Observable<Restaurant>

    @GET(RetrofitEndpoints.HOTEL_SEARCH_URL)
    fun getHotels(
            @Query("name") name: String,
            @Query("nation") nation: String,
            @Query("town") town: String,
            @Query("priceRange") priceRange: Int,
            @Query("limit") limit: Int,
            @Query("page") page: Int
    ): Observable<Hotel>

    @GET(RetrofitEndpoints.ATTRACTION_SEARCH_URL)
    fun getAttractions(
            @Query("name") name: String,
            @Query("nation") nation: String,
            @Query("town") town: String,
            @Query("priceRange") priceRange: Int,
            @Query("limit") limit: Int,
            @Query("page") page: Int
    ): Observable<Hotel>
}