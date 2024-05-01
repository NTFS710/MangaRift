package com.sephirita.mangarift.ui.model

import com.sephirita.mangarift.ui.components.sohprateste.Chapter

data class FormatedChapters(
    val chapters: Map<Float, List<Chapter>> = emptyMap()
)