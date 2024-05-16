package com.sephirita.mangarift.ui.di


import com.sephirita.mangarift.ui.components.detail.usecase.ChapterPagesUseCase
import com.sephirita.mangarift.ui.components.detail.usecase.MangaChaptersUseCase
import com.sephirita.mangarift.ui.components.detail.usecase.MangaDetailsUseCase
import com.sephirita.mangarift.ui.components.detail.viewmodel.DetailViewModel
import com.sephirita.mangarift.ui.components.home.usecase.LatestUpdatesUseCase
import com.sephirita.mangarift.ui.components.home.usecase.PopularNewTitlesUseCase
import com.sephirita.mangarift.ui.components.home.usecase.RecentlyAddedUseCase
import com.sephirita.mangarift.ui.components.home.usecase.SeasonUseCase
import com.sephirita.mangarift.ui.components.home.viewmodel.HomeViewModel
import com.sephirita.mangarift.ui.components.reader.ReaderViewModel
import com.sephirita.mangarift.ui.components.search.usecase.MangaWithTitleUseCase
import com.sephirita.mangarift.ui.components.search.viewModel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModules = module {
    viewModel {
        HomeViewModel(
            get(),
            get(),
            get(),
            get()
        )
    }
    viewModel {
        DetailViewModel(
            get(),
            get()
        )
    }
    viewModel {
        ReaderViewModel(
            get()
        )
    }
    viewModel {
        SearchViewModel(
            get()
        )
    }
    factory {
        PopularNewTitlesUseCase(get())
    }
    factory {
        LatestUpdatesUseCase(get())
    }
    factory {
        RecentlyAddedUseCase(get())
    }
    factory {
        MangaDetailsUseCase(get())
    }
    factory {
        MangaWithTitleUseCase(get())
    }
    factory {
        MangaChaptersUseCase(get())
    }
    factory {
        ChapterPagesUseCase(get())
    }
    factory {
        SeasonUseCase(get())
    }
}