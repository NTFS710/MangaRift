package com.sephirita.mangarift.presentation.di


import com.sephirita.mangarift.domain.usecase.ChapterPagesUseCase
import com.sephirita.mangarift.domain.usecase.MangaChaptersUseCase
import com.sephirita.mangarift.domain.usecase.MangaDetailsUseCase
import com.sephirita.mangarift.domain.usecase.LatestUpdatesUseCase
import com.sephirita.mangarift.domain.usecase.PopularNewTitlesUseCase
import com.sephirita.mangarift.domain.usecase.RecentlyAddedUseCase
import com.sephirita.mangarift.domain.usecase.SeasonUseCase
import com.sephirita.mangarift.domain.usecase.MangaWithTitleUseCase
import com.sephirita.mangarift.presentation.screen.detail.viewmodel.DetailViewModel
import com.sephirita.mangarift.presentation.screen.home.viewmodel.HomeViewModel
import com.sephirita.mangarift.presentation.screen.reader.viewmodel.ReaderViewModel
import com.sephirita.mangarift.presentation.screen.search.viewmodel.SearchViewModel
import com.sephirita.mangarift.presentation.screen.splash.viewmodel.SplashViewModel
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
            get(),
            get(),
            get(),
            get()
        )
    }
    viewModel {
        SplashViewModel()
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