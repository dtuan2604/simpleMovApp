package com.example.duonghoang_4020_p2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response


class PopularMovieFr: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pop_movie_fragment, container, false)
    }

    override fun onViewCreated(itemview: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemview, savedInstanceState)

        val progressBar = view?.findViewById<ProgressBar>(R.id.pbProgressBar)
        val rvMovies = view?.findViewById<RecyclerView>(R.id.rvMovies)

        val request = ServiceBuilder.buildService(TmdbEndpoints::class.java)
        val call = request.getPopMovies("0a7d3e2320c9bc4040bdec2b342e56dd")

        call.enqueue(object: retrofit2.Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if(response.isSuccessful){
                    progressBar?.visibility = View.GONE
                    rvMovies?.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this.context)
                        adapter = MoviesAdapter(response.body()!!.results)
                    }
                }
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                Toast.makeText(context, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}