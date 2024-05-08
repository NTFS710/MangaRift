package com.sephirita.mangarift.ui.components.reader

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel

@Composable
fun ReaderChapter(chapterId: String) {
    val viewModel: ReaderViewModel = koinViewModel()
    val state by viewModel.chapterPages.collectAsState()

    LaunchedEffect(key1 = viewModel) {
        viewModel.getChapterToRead(chapterId)
    }
//    val pdf = listOf(
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/1-0aa48b58eaa8a97a51cbfcf7209cf7d7e0469fc009239a5bf3b67c2ded45ed41.jpg",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/2-58a4664ebf790a8b5ee30e4eb1b5b71b99a14b4ca35d3b934e5bc64beac12eda.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/3-7f0d6b5c326e9bd997f8384134083b43d6dddcd3c3aac7852133a0f813a7c668.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/4-b53f65dd3750b0e37cab04b71be3221de23342e2a365338349d1c0008cc6d20f.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/5-c04c748430a32e7da71042b1ce87774a3747b53cdfe46bc0990a0ea1e864f103.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/6-19c1f628fdc7c02a49047346f2ed1b394981bf5621fca12a63a028768bbae900.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/7-4f91dba8795ebda4d922328b747d1d9bcec762c3fca060abdc07e67558094e0e.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/8-04e4fba8342e2a6bcf1945d45677f11b71d620b9a39ed7298200e63bca4ec31c.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/9-00c51d667b7100d9a4bb3d9df9db55aebe6034c271d981c910eb1ea63b4c5fee.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/10-5b128297914ae59a96bc2f1bfae904f1c05fdb8465423ea1f3d2700f11c37304.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/11-c24806cc377a2a1c75439487035d48c58e686a72622e48f5319a1024c5bcb65b.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/12-4e2484ca6a5d7813b72e114d147a95c9aedc3318ec938dd7bf45e56875f49863.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/13-d9199aa290c1729405f4da84a6f30539946988b06060623e921637d1d4ca0056.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/14-7e86842e7e65154f5d5a6cad080a8cc470b91054f07cc843d60e858475c3ce88.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/15-f9b2a1a291d0a01457f9021be3cab964b46d972e0a8e2d546380686724c97dee.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/16-a42a4592d374a0cac3d63ced44e49478d5ea7bfc54f0dcb900d6f6724410391b.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/17-3c342cb41a1c7a0e11cccb6fd85b9c82e615f6c70da6ad3c5ea113bb7982391b.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/18-31e5d11eca647458afaa82557a80681ad4e076fdc0656edf6179729e251950f2.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/19-b7156727418c5c85272312fe0607b6d4176440202ea12e27818e52e162e8eabe.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/20-cf703047fa5b1a881db9b073bd5505f62b739220f725ea92c06e48515703aeac.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/21-5b348ce6c31de57db7cc1cece15fac79d5bb47e79f47bd333c4d6a64cc01eed9.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/22-96a3455c937e62a8f124565629d539970057b290f3ca0c7b151be6216f89af6b.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/23-17279aa790f3356191fcd7ddf18cb7dd2ed4e0b217e18cd25ee76487488ce468.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/24-395821654cedba87b21601cc18353a2d24d94973cf8e703ba457756c115a2f80.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/25-69e8da827846ed4432d438a985af35704ebd98bbbab75de07e2479ac9c302440.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/26-9b61a2b8c92a28126385d3b90cc845a0c7683568f9979c77b986245ddaf9bfb6.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/27-432487ddc9f04515789db52a67502d7e6518277bed9db81eaae55a39a5f2cd03.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/28-553c1d4330a9856068e2cce8fdee9c481ebce45f5a812dacfce273556e65e70a.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/29-c30e96a98bf60f4082940a8ca441e0ba5bf3a17fe3567a56f2efec304a25bc52.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/30-ff2e0f3ca4af8f74e4eb83e7c10fdff5f34e853ff33a7f735903509aa2b99e6e.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/31-2fe5af82b371d60f1882d43c7bae0615d001c3abcccd2ffb55a8083b84a5d6a8.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/32-6ea533ef46cc52d0f0268eb9ac621053dbe7f27588b64ed9cde3b94e41ccbac8.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/33-17178785125fa0c86d5215063c07530a5a96882635781041923fdda64796419b.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/34-d825ea99f1016472b059a16a4f6937bad18c6587d632cb5f2f782040d2b910d2.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/35-cad23035ec8ad5cc2be28d28de153198b2f767eac0e3cb03f00441f64c7954ee.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/36-a3b10c6d090d1344207885e2299a5c1689b49e7fa14c1f265552dde9cc49b561.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/37-dd9acf530855797fed89d29f4d5d27cedee2641d0b511e0f0a8dfa1ddce92d69.png",
//        "https://cmdxd98sb0x3yprd.mangadex.network/data/8319095bdddb6db0717c3f3c8a67d809/38-63c31c791452f6a8dbf5994f8f992a59b51e455a866fefb69d33e729eb639eaa.jpg"
//    )
    val pagerState = rememberPagerState(pageCount = { state.size })
    HideSystemBars()
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize(),
        beyondViewportPageCount = 3
    ) {
        val currentItem = state[it]
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit,
            alignment = Alignment.Center,
            model = currentItem,
            contentDescription = "PDF Image"
        )
    }
}

@Composable
fun HideSystemBars() {
    val context = LocalContext.current

    DisposableEffect(Unit) {
        val window = context.findActivity()?.window ?: return@DisposableEffect onDispose {}
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)

        insetsController.apply {
            hide(WindowInsetsCompat.Type.statusBars())
            hide(WindowInsetsCompat.Type.navigationBars())
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        onDispose {
            insetsController.apply {
                show(WindowInsetsCompat.Type.statusBars())
                show(WindowInsetsCompat.Type.navigationBars())
                systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_DEFAULT
            }
        }
    }
}

fun Context.findActivity(): Activity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    return null
}