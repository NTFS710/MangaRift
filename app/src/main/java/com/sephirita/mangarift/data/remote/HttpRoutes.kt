package com.sephirita.mangarift.data.remote

object HttpRoutes {
    private const val BASE_URL = "https://api.mangadex.org"
    const val MANGA = "$BASE_URL/manga"
    const val CHAPTER = "$BASE_URL/chapter"
    const val RECENTLY_ADDED = "https://api.mangadex.org/manga?limit=15&contentRating[]=safe&contentRating[]=suggestive&contentRating[]=erotica&order[createdAt]=desc&includes[]=cover_art&hasAvailableChapters=true"
    const val POPULAR_NEW_TITLES = "https://api.mangadex.org/manga?limit=15&includes[]=cover_art&includes[]=artist&includes[]=author&order[followedCount]=desc&contentRating[]=safe&contentRating[]=suggestive&hasAvailableChapters=true&createdAtSince=2024-04-05T03%3A00%3A00"
    const val LATEST_UPDATES = "https://api.mangadex.org/manga?limit=15&includes[]=cover_art&hasAvailableChapters=true&order[latestUploadedChapter]=desc"
}