package com.example.duonghoang_4020_p2

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MoviesAdapter(val movies: List<MovResult>): RecyclerView.Adapter<MoviesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent,false)
        return MoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        return holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}

class MoviesViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

    private val photo: ImageView = itemView.findViewById(R.id.ivMoviePhoto)
    private val title: TextView = itemView.findViewById(R.id.tvMovieTitle)
    private val overview: TextView = itemView.findViewById(R.id.tvMovieOverview)
    private val rating: TextView = itemView.findViewById(R.id.tvMovieRating)

    @SuppressLint("SetTextI18n")
    fun bind(movie: MovResult){
        Glide.with(itemView.context).load("https://image.tmdb.org/t/p/w500${movie.poster_path}").into(photo)
        title.text = movie.title
        overview.text = movie.overview
        rating.text = "Rating: " + movie.vote_average.toString()
    }
}
class PeopleAdapter(val people: List<PeopleResult>): RecyclerView.Adapter<PeopleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.person_item,parent,false)
        return PeopleViewHolder(view)
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        return holder.bind(people[position])
    }

    override fun getItemCount(): Int {
        return people.size
    }
}

class PeopleViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

    private val photo: ImageView = itemView.findViewById(R.id.ivPeoplePhoto)
    private val name: TextView = itemView.findViewById(R.id.tvName)
    private val popularity: TextView = itemView.findViewById(R.id.tvPopularity)

    @SuppressLint("SetTextI18n")
    fun bind(people: PeopleResult){
        Glide.with(itemView.context).load("https://image.tmdb.org/t/p/w500${people.profile_path}").into(photo)
        name.text = people.name
        popularity.text = "Popularity: " + people.popularity.toString() + "%"
    }
}