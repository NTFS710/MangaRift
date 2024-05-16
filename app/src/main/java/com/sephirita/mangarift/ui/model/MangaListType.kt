package com.sephirita.mangarift.ui.model

enum class MangaListType {
    PopularNewTitles {
        override val title: String
        get() = "Novos Títulos Populares"
    },
    Seasonal {
        override val title: String
            get() = "Temporada"
    },
    LatestUpdates {
        override val title: String
            get() = "Últimas Atualizações"
    },
    RecentlyAdded {
        override val title: String
            get() = "Recentemente Adicionados"
    };

    abstract val title: String
}