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

class PopularPeopleFr: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pop_people_fragment, container, false)
    }

    override fun onViewCreated(itemview: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemview, savedInstanceState)

        val progressBar = view?.findViewById<ProgressBar>(R.id.pbPpProgressBar)
        val rvPeople = view?.findViewById<RecyclerView>(R.id.rvPeople)

        val request = ServiceBuilder.buildService(TmdbEndpoints::class.java)
        val call = request.getPopPeople("0a7d3e2320c9bc4040bdec2b342e56dd")

        call.enqueue(object: retrofit2.Callback<People> {
            override fun onResponse(call: Call<People>, response: Response<People>) {
                if(response.isSuccessful){
                    progressBar?.visibility = View.GONE
                    rvPeople?.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this.context)
                        adapter = PeopleAdapter(response.body()!!.results)
                    }
                }
            }

            override fun onFailure(call: Call<People>, t: Throwable) {
                Toast.makeText(context, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}