package com.sephirita.mangarift

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sephirita.mangarift.ui.components.search.SearchBar
import com.sephirita.mangarift.ui.components.home.HomeItemsList
import com.sephirita.mangarift.ui.components.banner.BannersHome
import com.sephirita.mangarift.ui.components.detail.DetailPage
import com.sephirita.mangarift.ui.components.search.SearchList
import com.sephirita.mangarift.ui.theme.MangaRiftTheme
import com.sephirita.mangarift.utils.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge() // Funciona só na activity que chamou, se botar outra tem que chamar novamente
        super.onCreate(savedInstanceState)
        setContent {
            MangaRiftTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    telaDeDetalhes()
//                    telaInicial()
//                    telaDeBusca()
                }
            }
        }
    }
}

@Composable
private fun telaDeBusca() {
    Column {
        SearchBar()
        Spacer(modifier = Modifier.height(16.dp))
        SearchList(searchItems = getMangaList())
    }
}

@Composable
private fun telaInicial() {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BannersHome(items = getMangaList())
        Spacer(modifier = Modifier.height(16.dp))
        HomeItemsList(listTitle = "teste 1", items = getMangaList())
        Spacer(modifier = Modifier.height(16.dp))
        HomeItemsList(listTitle = "teste 2", items = getMangaList())
        Spacer(modifier = Modifier.height(16.dp))
        HomeItemsList(listTitle = "teste 3", items = getMangaList())
        Spacer(modifier = Modifier.height(16.dp))
        HomeItemsList(listTitle = "teste 4", items = getMangaList())
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun telaDeDetalhes() {
    DetailPage(item = getMockedManga())
}