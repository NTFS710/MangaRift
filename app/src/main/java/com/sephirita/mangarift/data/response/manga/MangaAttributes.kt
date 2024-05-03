package com.sephirita.mangarift.data.response.manga

data class MangaAttributes(
    val title: List<Map<String, String>>?, // Lista -> sigla da lingua - Título na lingua
    val altTitles: List<Map<String, String>>?, // Lista -> sigla da lingua - Título alternativo na lingua
    val availableTranslatedLanguages: List<String>?,
    val chapterNumbersResetOnNewVolume: Boolean?,
    val contentRating: String?,
    val createdAt: String?,
    val description: List<Map<String, String>>?, // Lista -> sigla da lingua - Descrição na lingua
    val isLocked: Boolean?,
    val lastChapter: String?,
    val lastVolume: String?,
    val latestUploadedChapter: String?,
    val links: List<Map<String, String>>?, // Sinceramente não sei oq diabo é isso kkkkkkkkkk
    val originalLanguage: String?,
    val publicationDemographic: String?,
    val state: String?,
    val status: String?,
    val tags: List<Tag>?,
    val updatedAt: String?,
    val version: Int?,
    val year: Int?
)