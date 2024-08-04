package com.sephirita.mangarift.presentation.screen.home.state

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.sephirita.mangarift.domain.model.Manga

data class HomeState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val popularNewTitles: SnapshotStateList<Manga> = mutableStateListOf(),
    val season: SnapshotStateList<Manga> = mutableStateListOf(),
    val latestUpdates: SnapshotStateList<Manga> = mutableStateListOf(),
    val recentlyAdded: SnapshotStateList<Manga> = mutableStateListOf()
)