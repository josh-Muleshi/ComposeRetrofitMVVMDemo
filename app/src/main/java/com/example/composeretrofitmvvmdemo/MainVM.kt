package com.example.composeretrofitmvvmdemo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.example.composeretrofitmvvmdemo.dao.Movie
import com.example.composeretrofitmvvmdemo.retrofit.RemoteService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainVM() : ViewModel() {
    private val mainRepo = MainRepo()
    private var _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>>
        get() = _movieList

    fun getPopularMovies() = liveData {
        val movieList = mainRepo.getMovies()
        emit(movieList)
    }

    fun getMovies() {
        viewModelScope.launch(IO) {
            _movieList.postValue(mainRepo.getMovies())
        }
    }

    var movieListResponse:List<Movie> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")
    fun getMovieList() {
        viewModelScope.launch {
            try {
                val movieList = mainRepo.getMovies()
                movieListResponse = movieList
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

}
