package com.sephirita.mangarift.ui.model

import com.sephirita.mangarift.domain.model.Chapter

data class FormatedChapters(
    val order: ChaptersOrder = ChaptersOrder.Natural,
    val natural: Map<Float, List<Chapter>> = emptyMap(),
    val reversed: Map<Float, List<Chapter>> = emptyMap()
)