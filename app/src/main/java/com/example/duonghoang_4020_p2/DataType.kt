package com.example.duonghoang_4020_p2

data class Movies(
    val results: List<MovResult>
)
data class MovResult(
    val id: Int,
    val overview: String,
    val poster_path: String,
    val releaseDate: String,
    val title: String,
    val vote_average: Double,
    val voteCount: Int
)

data class People(
    val results: List<PeopleResult>
)
data class PeopleResult(
    val profile_path: String,
    val id: Int,
    val name: String,
)