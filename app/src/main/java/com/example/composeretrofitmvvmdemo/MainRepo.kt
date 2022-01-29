package com.example.composeretrofitmvvmdemo

import android.util.Log
import com.example.composeretrofitmvvmdemo.dao.Movie
import com.example.composeretrofitmvvmdemo.dao.MovieList
import com.example.composeretrofitmvvmdemo.retrofit.RemoteService
import retrofit2.Response

class MainRepo() {

    private val apiKey = BuildConfig.API_KEY

    suspend fun getMovies():List<Movie> {
        lateinit var movieList: List<Movie>

        try {
            val response: Response<MovieList> = RemoteService.getRemoteService().getPopularMovies(apiKey)
            val body: MovieList? = response.body()
            if (body != null) {
                movieList = body.results
            }
        } catch (exe: Exception) {
            Log.i("MyTag", exe.message.toString())
        }

        return movieList
    }

}