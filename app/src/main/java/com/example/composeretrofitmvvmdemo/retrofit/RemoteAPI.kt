package com.example.composeretrofitmvvmdemo.retrofit

import com.example.composeretrofitmvvmdemo.dao.MovieList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteAPI {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") api_key: String,
    ): Response<MovieList>

}