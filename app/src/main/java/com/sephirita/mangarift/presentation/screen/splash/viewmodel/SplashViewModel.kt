package com.sephirita.mangarift.presentation.screen.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sephirita.mangarift.presentation.model.BlurAnimation
import com.sephirita.mangarift.presentation.screen.splash.state.SplashState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    private val _splashState = MutableStateFlow(SplashState())
    val splashState: StateFlow<SplashState> = _splashState.asStateFlow()

    init {
        startAnimation()
    }

    private fun startAnimation() {
        viewModelScope.launch {
            delay(1000)
            _splashState.update {
                SplashState(false)
            }
        }
    }
}
