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
import com.sephirita.mangarift.ui.components.search.SearchBarSample
import com.sephirita.mangarift.ui.components.home.StandardMangaList
import com.sephirita.mangarift.ui.components.banner.BannersHome
import com.sephirita.mangarift.ui.components.detail.DetailPage
import com.sephirita.mangarift.ui.components.home.getMangaList
import com.sephirita.mangarift.ui.components.home.getMockedManga
import com.sephirita.mangarift.ui.theme.MangaRiftTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MangaRiftTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DetailPage(item = getMockedManga())
//                    telaDeBusca()
                }
            }
        }
    }
}

@Composable
private fun telaDeBusca() {
    SearchBarSample()
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
        StandardMangaList(listTitle = "teste 1", items = getMangaList())
        Spacer(modifier = Modifier.height(16.dp))
        StandardMangaList(listTitle = "teste 2", items = getMangaList())
        Spacer(modifier = Modifier.height(16.dp))
        StandardMangaList(listTitle = "teste 3", items = getMangaList())
        Spacer(modifier = Modifier.height(16.dp))
        StandardMangaList(listTitle = "teste 4", items = getMangaList())
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun telaDeDetalhes() {
    DetailPage(item = getMockedManga())
}