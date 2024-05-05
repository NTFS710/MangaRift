package com.sephirita.mangarift.ui.model

enum class MangaListType {
    PopularNewTitles {
        override val title: String
        get() = "Novos Títulos Populares"
    },
    LatestUpdates {
        override val title: String
            get() = "Últimas Atualizações"
    },
    RecentlyAdded {
        override val title: String
            get() = "Recentemente Adicionados"
    };
//    Seasonal {
//        override val title: String
//            get() = "Sazonal"
//    };

    abstract val title: String
}