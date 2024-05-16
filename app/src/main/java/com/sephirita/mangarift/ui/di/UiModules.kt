package com.sephirita.mangarift.ui.di


import com.sephirita.mangarift.domain.usecase.ChapterPagesUseCase
import com.sephirita.mangarift.domain.usecase.MangaChaptersUseCase
import com.sephirita.mangarift.domain.usecase.MangaDetailsUseCase
import com.sephirita.mangarift.ui.screen.detail.viewmodel.DetailViewModel
import com.sephirita.mangarift.domain.usecase.LatestUpdatesUseCase
import com.sephirita.mangarift.domain.usecase.PopularNewTitlesUseCase
import com.sephirita.mangarift.domain.usecase.RecentlyAddedUseCase
import com.sephirita.mangarift.domain.usecase.SeasonUseCase
import com.sephirita.mangarift.ui.screen.home.viewmodel.HomeViewModel
import com.sephirita.mangarift.ui.screen.reader.viewmodel.ReaderViewModel
import com.sephirita.mangarift.domain.usecase.MangaWithTitleUseCase
import com.sephirita.mangarift.ui.screen.search.viewModel.SearchViewModel
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