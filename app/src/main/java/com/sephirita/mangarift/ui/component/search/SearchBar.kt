package com.sephirita.mangarift.ui.component.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    onSearch: (String) -> Unit
) {
    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        SearchBar(
            modifier = Modifier.align(Alignment.Center),
            query = text,
            onQueryChange = { text = it },
            onSearch = {
                onSearch(text)
                // Loading Here
                active = false
            },
            active = active,
            onActiveChange = {
                active = it
            },
            placeholder = { Text("Escreva o nome da obra") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) },
        ) {
//            repeat(4) { idx ->
//                val resultText = "Suggestion $idx"
//                ListItem(
//                    headlineContent = { Text(resultText) },
//                    supportingContent = { Text("Additional info") },
//                    leadingContent = { Icon(Icons.Filled.Star, contentDescription = null) },
//                    modifier = Modifier
//                        .clickable {
//                            text = resultText
//                            active = false
//                        }
//                        .fillMaxWidth()
//                        .padding(horizontal = 16.dp, vertical = 4.dp)
//                )
//            }
        }
    }
}
