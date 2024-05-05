package com.sephirita.mangarift.ui.di


import com.sephirita.mangarift.ui.components.detail.viewmodel.DetailViewModel
import com.sephirita.mangarift.ui.components.home.usecase.LatestUpdatesUseCase
import com.sephirita.mangarift.ui.components.home.usecase.PopularNewTitlesUseCase
import com.sephirita.mangarift.ui.components.home.usecase.RecentlyAddedUseCase
import com.sephirita.mangarift.ui.components.home.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModules = module {
    viewModel {
        HomeViewModel(
            get(),
            get(),
            get()
        )
    }
    viewModel {
        DetailViewModel()
    }
    factory {
        PopularNewTitlesUseCase(get())
    }
    factory {
        RecentlyAddedUseCase(get())
    }
    factory {
        LatestUpdatesUseCase(get())
    }
}