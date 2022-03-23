package com.example.duonghoang_4020_p2

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbEndpoints {
    @GET("3/movie/popular")
    fun getPopMovies(@Query("api_key") key: String): Call<Movies>

    @GET("3/movie/upcoming")
    fun getUpcomingMovies(@Query("api_key") key: String): Call<Movies>

    @GET("3/person/popular")
    fun getPopPeople(@Query("api_key") key: String): Call<People>

}