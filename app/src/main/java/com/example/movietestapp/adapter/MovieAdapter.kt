package com.example.movietestapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movietestapp.mvvm.model.MovieDataDetail
import com.example.movietestapp.databinding.MovieItemListBinding

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var movieList = ArrayList<MovieDataDetail>()
    private var onClickListener: OnClickListener? = null

    fun setMovieList(movieList: List<MovieDataDetail>) {
        this.movieList = movieList as ArrayList<MovieDataDetail>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MovieItemListBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/w500"+movieList[position].poster_path)
            .into(holder.binding.movieIv)
        holder.binding.movieNameTv.text = movieList[position].title
        holder.itemView.setOnClickListener{
            onClickListener?.onClick(position, movieList[position])
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setOnClickListener(listener: OnClickListener) {
        this.onClickListener = listener
    }

    interface OnClickListener {
        fun onClick(position: Int, movieData: MovieDataDetail)
    }

    fun filterList(filterList: ArrayList<MovieDataDetail>) {
        movieList = filterList
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: MovieItemListBinding): RecyclerView.ViewHolder(binding.root) {

    }
}