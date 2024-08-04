package com.sephirita.mangarift.presentation

import androidx.compose.foundation.lazy.LazyListState

val LazyListState.isLastItemVisible: Boolean
    get() = layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1

val LazyListState.isFirstItemVisible: Boolean
    get() = firstVisibleItemIndex == 0