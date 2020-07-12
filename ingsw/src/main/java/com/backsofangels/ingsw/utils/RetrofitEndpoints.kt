package com.backsofangels.ingsw.utils

object RetrofitEndpoints {
    //For online usage
    //const val SERVER_BASE_URL = "https://backsofangels.serveousercontent.com"

    //For local usage
    const val SERVER_BASE_URL = "http://192.168.1.155"
    const val STRUCTURE_SEARCH_URL = "/structure/get/"
    const val RESTAURANT_SEARCH_URL = "/structure/get/restaurants"
    const val HOTEL_SEARCH_URL = "/structure/get/hotels"
    const val ATTRACTION_SEARCH_URL = "/structure/get/attractions"
    const val REVIEW_GET_FOR_STRUCTURE = "/reviews/get/structure"
    const val REVIEW_GET_FOR_USER = "/reviews/get/user"
    const val REVIEW_POST = "/reviews/post/review"
    const val REVIEW_UPDATE = "/reviews/update/review"
    const val IMAGES_MODULE = "/images/"
    const val AUTH_LOGIN = "/auth/login"
    const val AUTH_SIGNUP = "/auth/signup"
    const val AUTH_REFRESHTOKEN = "/auth/refreshToken"
}