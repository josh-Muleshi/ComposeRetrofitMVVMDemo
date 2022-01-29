package com.example.composeretrofitmvvmdemo

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

}
