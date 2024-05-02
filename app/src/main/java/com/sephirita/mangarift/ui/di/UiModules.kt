package com.sephirita.mangarift.ui.di


import com.sephirita.mangarift.ui.components.detail.viewmodel.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModules = module {
    viewModel {
        DetailViewModel()
    }
}