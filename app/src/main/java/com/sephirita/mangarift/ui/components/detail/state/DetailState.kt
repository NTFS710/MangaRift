package com.sephirita.mangarift.ui.components.detail.state

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateMap
import com.sephirita.mangarift.domain.model.Manga

data class DetailState(
    val isLoading: Boolean = true,
    val manga: Manga = Manga(),
    val expandedChapter: SnapshotStateMap<Float, Boolean> = mutableStateMapOf(),
    val isError: Boolean = false
)
