package com.example.movietestapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.movietestapp.databinding.ActivityMovieDetailBinding
import com.example.movietestapp.mvvm.model.MovieDataDetail
import com.google.gson.Gson

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var movieData: MovieDataDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        if (intent.hasExtra("movieData")) {
            val movieDataString = intent.getSerializableExtra("movieData").toString()
            movieData = Gson().fromJson(movieDataString, MovieDataDetail::class.java)
        }

        setData()
    }

    private fun setData() {
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500"+movieData.poster_path)
            .into(binding.movieIv)
        binding.movieNameTv.text = movieData.title
        binding.voteAverageTv.text = "Vote Average: " + movieData.vote_average.toString()
        binding.voteCountTv.text = "Vote Count: " + movieData.vote_count.toString()
    }
}