package com.sephirita.mangarift.ui.components.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sephirita.mangarift.ui.components.card.Tag
import com.sephirita.mangarift.domain.model.Tag

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Genres(
    modifier: Modifier = Modifier,
    titleText: String = "Gêneros",
    tags: List<Tag> = emptyList()
) {
    if (tags.isNotEmpty()) {
        Box(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Column {

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = titleText,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    maxLines = 2
                ) {
                    tags.forEach {
                        Tag(
                            name = it.type,
                            background = MaterialTheme.colorScheme.surface
                        )
                    }
                }
            }
        }
    }
}