package com.sephirita.mangarift.presentation.model

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

enum class DetailsTab(val index: Int) {
    Details(0) {
        @Composable
        override fun getText(): String {
            return "Detalhes"
        }

        @Composable
        override fun getCorners(): RoundedCornerShape {
            return RoundedCornerShape(bottomEnd = 8.dp)
        }
    },
    Chapters(1) {
        @Composable
        override fun getText(): String {
            return "Capítulos"
        }

        @Composable
        override fun getCorners(): RoundedCornerShape {
            return RoundedCornerShape(bottomStart = 8.dp)
        }
    };

    @Composable
    abstract fun getText(): String

    @Composable
    abstract fun getCorners(): RoundedCornerShape
}