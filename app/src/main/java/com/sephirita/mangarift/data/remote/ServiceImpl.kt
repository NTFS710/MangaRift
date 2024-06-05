package com.sephirita.mangarift.data.remote

import com.sephirita.mangarift.data.remote.dto.Service
import com.sephirita.mangarift.data.remote.dto.model.ChapterListResponse
import com.sephirita.mangarift.data.remote.dto.model.DetailedMangaResponse
import com.sephirita.mangarift.data.remote.dto.model.MangaListResponse
import com.sephirita.mangarift.data.remote.dto.model.SeasonResponse
import com.sephirita.mangarift.data.remote.dto.model.chapter.page.ChapterPagesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ServiceImpl(
    private val client: HttpClient
) : Service {
    override suspend fun getPopularNewTitles(createdSinceDate: String): MangaListResponse {
        return client.get(HttpRoutes.MANGA) {
            url {
                encodedParameters.append("createdAtSince", createdSinceDate) // pegar data do dia, as 3 da manhã
                encodedParameters.append("order[followedCount]", "desc")
                encodedParameters.append("hasAvailableChapters", "true")
                encodedParameters.append("availableTranslatedLanguage[]", "pt-br")
                encodedParameters.append("availableTranslatedLanguage[]", "en")
                encodedParameters.append("includes[]", "cover_art")
                encodedParameters.append("includes[]", "author")
                encodedParameters.append("contentRating[]", "safe")
                encodedParameters.append("contentRating[]", "erotica")
                encodedParameters.append("contentRating[]", "suggestive")
                encodedParameters.append("limit", "15")
            }
        }.body()
    }

    override suspend fun getRecentlyAdded(limit: Int): MangaListResponse {
        return client.get(HttpRoutes.MANGA) {
            url {
                encodedParameters.append("order[createdAt]", "desc")
                encodedParameters.append("hasAvailableChapters", "true")
                encodedParameters.append("availableTranslatedLanguage[]", "pt-br")
                encodedParameters.append("availableTranslatedLanguage[]", "en")
                encodedParameters.append("includes[]", "cover_art")
                encodedParameters.append("contentRating[]", "safe")
                encodedParameters.append("contentRating[]", "erotica")
                encodedParameters.append("contentRating[]", "suggestive")
                encodedParameters.append("limit", "$limit")
            }
        }.body()
    }

    override suspend fun getLatestUpdates(limit: Int): MangaListResponse {
        return client.get(HttpRoutes.MANGA) {
            url {
                encodedParameters.append("order[latestUploadedChapter]", "desc")
                encodedParameters.append("hasAvailableChapters", "true")
                encodedParameters.append("availableTranslatedLanguage[]", "pt-br")
                encodedParameters.append("availableTranslatedLanguage[]", "en")
                encodedParameters.append("includes[]", "cover_art")
                encodedParameters.append("contentRating[]", "safe")
                encodedParameters.append("contentRating[]", "erotica")
                encodedParameters.append("contentRating[]", "suggestive")
                encodedParameters.append("limit", "$limit")
            }
        }.body()
    }

    override suspend fun getMangaWithTitle(mangaTitle: String): MangaListResponse {
        return client.get(HttpRoutes.MANGA) {
            url {
                if (mangaTitle.isNotBlank()) encodedParameters.append("title", mangaTitle)
                encodedParameters.append("hasAvailableChapters", "true")
                encodedParameters.append("order[followedCount]", "desc")
                encodedParameters.append("availableTranslatedLanguage[]", "pt-br")
                encodedParameters.append("availableTranslatedLanguage[]", "en")
                encodedParameters.append("includes[]", "cover_art")
                encodedParameters.append("contentRating[]", "safe")
                encodedParameters.append("contentRating[]", "erotica")
                encodedParameters.append("contentRating[]", "suggestive")
                encodedParameters.append("limit", "100")
            }
        }.body()
    }

    override suspend fun getMangaDetails(mangaId: String): DetailedMangaResponse {
        return client.get("${HttpRoutes.MANGA}/$mangaId") {
            url {
                encodedParameters.append("includes[]", "cover_art")
            }
        }.body()
    }

    override suspend fun getSeasonMangaIds(): SeasonResponse {
        return client.get(HttpRoutes.SEASON).body()
    }

    override suspend fun getSeasonMangas(mangaIdList: List<String>, limit: Int): MangaListResponse {
        return client.get(HttpRoutes.MANGA) {
            url {
                mangaIdList.forEach {
                    encodedParameters.append("ids[]", it)
                }
                encodedParameters.append("hasAvailableChapters", "true")
                encodedParameters.append("order[followedCount]", "desc")
                encodedParameters.append("availableTranslatedLanguage[]", "pt-br")
                encodedParameters.append("availableTranslatedLanguage[]", "en")
                encodedParameters.append("includes[]", "cover_art")
                encodedParameters.append("contentRating[]", "safe")
                encodedParameters.append("contentRating[]", "erotica")
                encodedParameters.append("contentRating[]", "suggestive")
                encodedParameters.append("limit", "$limit")
            }
        }.body()
    }

    override suspend fun getChapters(mangaId: String, offset: Int): ChapterListResponse {
        return client.get("${HttpRoutes.MANGA}/$mangaId/feed") {
            url {
                encodedParameters.append("order[chapter]", "asc")
                encodedParameters.append("includeEmptyPages", "0")
                encodedParameters.append("offset", "$offset")
                encodedParameters.append("includes[]", "scanlation_group")
                encodedParameters.append("translatedLanguage[]", "pt-br")
                encodedParameters.append("translatedLanguage[]", "en")
                encodedParameters.append("contentRating[]", "safe")
                encodedParameters.append("contentRating[]", "erotica")
                encodedParameters.append("contentRating[]", "suggestive")
            }
        }.body()
    }

    override suspend fun getChapterPDF(chapterId: String): ChapterPagesResponse {
        return client.get("${HttpRoutes.CHAPTERS}/$chapterId").body()
    }
}