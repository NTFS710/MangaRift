package com.sephirita.mangarift.data.remote

object HttpRoutes {
    private const val BASE_URL = "https://api.mangadex.org"
    const val MANGA = "$BASE_URL/manga"
    const val CHAPTERS = "$BASE_URL/at-home/server"
    const val SEASON = "https://antsylich.github.io/mangadex-seasonal/seasonal-list.json"
}