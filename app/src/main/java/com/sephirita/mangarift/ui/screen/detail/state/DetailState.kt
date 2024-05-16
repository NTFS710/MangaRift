package com.sephirita.mangarift.ui.screen.detail.state

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import com.sephirita.mangarift.domain.model.Manga

data class DetailState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val manga: Manga = Manga(),
    val expandedChapter: SnapshotStateMap<Float, Boolean> = mutableStateMapOf()
)
