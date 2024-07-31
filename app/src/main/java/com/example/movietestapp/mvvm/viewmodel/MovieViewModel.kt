package com.example.movietestapp.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movietestapp.http.RetrofitInstance
import com.example.movietestapp.mvvm.model.MovieData
import com.example.movietestapp.mvvm.model.MovieDataDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieViewModel: ViewModel() {

    var movieData = MutableLiveData<List<MovieDataDetail>>()

    fun getPopularMovie() {
        RetrofitInstance.api.getPopularMovies("f3777ba25b5e60742e252c2252f7c09b").enqueue(object: Callback<MovieData> {
            override fun onResponse(call: Call<MovieData>, response: Response<MovieData>) {
                if (response.body() != null) {
                    movieData.value = response.body()!!.results
                } else {
                    return
                }
            }

            override fun onFailure(call: Call<MovieData>, t: Throwable) {

            }
        })
    }

    fun observeMovieData(): LiveData<List<MovieDataDetail>> {
        return movieData
    }

}