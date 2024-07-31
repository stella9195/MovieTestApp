package com.example.movietestapp.http.interfaces

import com.example.movietestapp.mvvm.model.MovieData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPI {

    @GET("popular?")
    fun getPopularMovies(@Query("api_key") api_key: String): Call<MovieData>

}