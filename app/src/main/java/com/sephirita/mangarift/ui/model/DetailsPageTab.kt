package com.sephirita.mangarift.ui.model

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

enum class DetailsPageTab {
    Details {
        @Composable
        override fun getText(): String {
            return "Detalhes"
        }

        @Composable
        override fun getCorners(): RoundedCornerShape {
            return RoundedCornerShape(bottomEnd = 8.dp)
        }

        override val index: Int
            get() = 0
    },
    Chapters {
        @Composable
        override fun getText(): String {
            return "Capítulos"
        }

        @Composable
        override fun getCorners(): RoundedCornerShape {
            return RoundedCornerShape(bottomStart = 8.dp)
        }

        override val index: Int
            get() = 1
    };

    @Composable
    abstract fun getText(): String

    @Composable
    abstract fun getCorners(): RoundedCornerShape

    abstract val index: Int
}
