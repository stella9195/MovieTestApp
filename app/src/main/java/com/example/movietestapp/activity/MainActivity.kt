package com.example.movietestapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movietestapp.activity.MovieDetailActivity
import com.example.movietestapp.adapter.MovieAdapter
import com.example.movietestapp.databinding.ActivityMainBinding
import com.example.movietestapp.mvvm.model.MovieDataDetail
import com.example.movietestapp.mvvm.viewmodel.MovieViewModel
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var movieArrayList: ArrayList<MovieDataDetail>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRecyclerView()
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        viewModel.getPopularMovie()
        viewModel.observeMovieData().observe(this, Observer {
            movieList -> movieArrayList = movieList as ArrayList<MovieDataDetail>
            movieAdapter.setMovieList(movieArrayList)
        })
        setSearchFilterView()
    }

    private fun setRecyclerView() {
        movieAdapter = MovieAdapter()
        binding.movieRv.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = movieAdapter
            movieAdapter.setOnClickListener(object : MovieAdapter.OnClickListener {
                override fun onClick(position: Int, movieData: MovieDataDetail) {
                    val intent = Intent(this@MainActivity, MovieDetailActivity::class.java)
                    intent.putExtra("movieData", Gson().toJson(movieData))
                    startActivity(intent)
                }
            })
        }
    }

    private fun setSearchFilterView() {
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener, android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(textFilter: String): Boolean {
                filterMovie(textFilter)
                return false
            }

        })
    }

    private fun filterMovie(textFilter: String) {
        val filterList: ArrayList<MovieDataDetail> = ArrayList()

        for (item in movieArrayList) {
            if (item.title.lowercase().contains(textFilter.lowercase())) {
                filterList.add(item)
            }
        }

        if (filterList.isEmpty()) {
            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show()
        } else {
            movieAdapter.filterList(filterList)
        }
    }
}
