package com.example.movietestapp.mvvm.model

data class MovieData(
    val page: Int,
    val results: List<MovieDataDetail>,
    val total_pages: Int,
    val total_results: Int
)