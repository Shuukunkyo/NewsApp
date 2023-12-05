package com.example.newsapp.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.MockData
import com.example.newsapp.MockData.getTimeAgo
import com.example.newsapp.NewsData
import com.example.newsapp.R

//News詳細画面
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(newsData: NewsData, scrollState: ScrollState, navController: NavController) {
    // ScaffoldでtopBarを含む詳細画面を構築
    Scaffold(topBar = {
        DetailTopAppBar(onBackPressed = { navController.popBackStack() })
    })
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 画面のタイトル
            Text(text = "Detail Screen", fontWeight = FontWeight.SemiBold)
            // ニュースの画像
            Image(
                painter = painterResource(id = newsData.image),
                contentDescription = ""
            )
            // 作者と投稿日時の表示
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InfoWithIcon(Icons.Default.Edit, info = newsData.author)
                InfoWithIcon(
                    Icons.Default.DateRange,
                    info = MockData.stringToDate(newsData.publishedAt).getTimeAgo()
                )
            }
            Text(text = newsData.title, fontWeight = FontWeight.Bold)
            Text(text = newsData.description, modifier = Modifier.padding(top = 16.dp))
        }
    }
}

//詳細画面のTopBar
@Composable
fun DetailTopAppBar(onBackPressed: () -> Unit = {}) {
    TopAppBar(title = { Text(text = "Detail Screen", fontWeight = FontWeight.SemiBold) },
        navigationIcon = {
            IconButton(onClick = { onBackPressed }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            }
        }
    )
}

//詳細画面　作者、日付表示部分のtitleとicon
@Composable
fun InfoWithIcon(icon: ImageVector, info: String) {
    Row {
        Icon(
            icon, contentDescription = "Author",
            modifier = Modifier.padding(end = 8.dp),
            colorResource(id = R.color.purple_500)
        )
        Text(text = info)
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(
        NewsData(
            2,
            image = R.drawable.mac_news,
            author = "Zaja Razk CNN",
            title = "Tiger King' Joe Exotic says he has been diagnosed with aggressivedescription",
            description = "Joseph Maldonado, known as Joe Exotic on the 2020 Netflix",
            publishedAt = "2021 -11- 04 "
        ),
        rememberScrollState(),
        rememberNavController()
    )
}