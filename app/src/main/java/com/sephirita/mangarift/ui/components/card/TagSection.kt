package com.sephirita.mangarift.ui.components.card

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sephirita.mangarift.domain.model.Tag

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagSection(
    modifier: Modifier = Modifier,
    titleText: String = "",
    titleSize: TextUnit = 16.sp,
    titleMaxLines: Int = 2,
    titleOverflow: TextOverflow = TextOverflow.Ellipsis,
    titleWeight: FontWeight? = FontWeight.Bold,
    titleHeight: TextUnit = TextUnit.Unspecified,
    spacerHeight: Dp = 8.dp,
    tags: List<Tag> = emptyList(),
    tagsMaxLines: Int = 2,
    tagBackground: Color = MaterialTheme.colorScheme.surface
) {
    if (tags.isNotEmpty()) {
        Box(modifier = modifier.fillMaxWidth()) {
            Column {
                if (titleText.isNotEmpty()) {
                    Text(
                        text = titleText,
                        fontSize = titleSize,
                        fontWeight = titleWeight,
                        maxLines = titleMaxLines,
                        lineHeight = titleHeight,
                        overflow = titleOverflow
                    )
                    Spacer(modifier = Modifier.height(spacerHeight))
                }
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    maxLines = tagsMaxLines
                ) {
                    tags.forEach {
                        Tag(
                            name = it.type,
                            background = tagBackground
                        )
                    }
                }
            }
        }
    }
}