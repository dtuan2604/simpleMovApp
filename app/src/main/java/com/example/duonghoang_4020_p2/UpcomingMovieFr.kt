package com.example.duonghoang_4020_p2

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Response

class UpcomingMovieFr: Fragment() {
    var movies: List<MovResult>? = null
    var adapter: ArrayAdapter<String>? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.upcoming_mov_fragment, container, false)
    }
    override fun onViewCreated(itemview: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemview, savedInstanceState)

        val progressBar = view?.findViewById<ProgressBar>(R.id.spProgressBar)
        val spMovies = itemview.findViewById<Spinner>(R.id.spMovies)
        val cardView = itemview.findViewById<CardView>(R.id.spCardView)
        spMovies?.visibility = View.GONE
        cardView?.visibility = View.GONE


        val request = ServiceBuilder.buildService(TmdbEndpoints::class.java)
        val call = request.getUpcomingMovies("0a7d3e2320c9bc4040bdec2b342e56dd")

        call.enqueue(object: retrofit2.Callback<Movies>{
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if(response.isSuccessful){
                    movies = response.body()!!.results
                    val titles = movies?.map{it.title}
                    adapter = ArrayAdapter<String>(itemview.context, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                        titles!!.toList()
                    )
                    progressBar?.visibility = View.GONE
                    spMovies.adapter = adapter
                }
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Toast.makeText(context, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })


        spMovies?.visibility = View.VISIBLE
        cardView?.visibility = View.VISIBLE

        val photo: ImageView = itemview.findViewById(R.id.ivPhotoSpinner)
        val title: TextView = itemview.findViewById(R.id.tvTitleSpinner)
        val overview: TextView = itemview.findViewById(R.id.tvOverviewSpinner)
        val rating: TextView = itemview.findViewById(R.id.tvRatingSpinner)

        spMovies?.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            @SuppressLint("SetTextI18n")
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val movie = movies!![position]
                Glide.with(itemview.context).load("https://image.tmdb.org/t/p/w500${movie.poster_path}").into(photo)
                title.text = movie.title
                overview.text = movie.overview
                rating.text = "Rating: " + movie.vote_average.toString()

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

    }

}