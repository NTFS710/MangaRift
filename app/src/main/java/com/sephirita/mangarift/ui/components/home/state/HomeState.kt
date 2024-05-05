package com.sephirita.mangarift.ui.components.home.state

data class HomeState(
    val isLoading: Boolean = true,
    val isLoaded: Boolean = false,
    val isError: Boolean = false
)