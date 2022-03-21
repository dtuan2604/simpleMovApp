package com.example.duonghoang_4020_p2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.duonghoang_4020_p2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var mainViewBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mainViewBinding.root)

        val popularMovieFr = PopularMovieFr()
        val popularPeopleFr = PopularPeopleFr()
        val upcomingMovieFr = UpcomingMovieFr()

        setFragment(popularMovieFr)
        mainViewBinding.bottomNavView.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.miMovie -> setFragment(popularMovieFr)
                R.id.miPeople -> setFragment(popularPeopleFr)
                R.id.miUpcomingMovie -> setFragment(upcomingMovieFr)
            }
            true
        }

    }

    private fun setFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
    }

}