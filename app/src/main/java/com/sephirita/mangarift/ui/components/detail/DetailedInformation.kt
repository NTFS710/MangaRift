package com.sephirita.mangarift.ui.components.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sephirita.mangarift.domain.model.Tag

@Composable
fun DetailedInformation(
    modifier: Modifier = Modifier,
    tags:  List<Tag>,
    description: String
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Column {
            Genres(tags = tags)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = description)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}