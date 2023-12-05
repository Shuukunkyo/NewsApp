package com.example.newsapp.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapp.MockData
import com.example.newsapp.MockData.getTimeAgo
import com.example.newsapp.NewsData

//TopNews一覧画面
@Composable
fun TopNews(navController: NavController) {
    Column(
        // 画面全体を覆うカラム
        modifier = Modifier.fillMaxWidth(),
        // 水平方向に中央揃え
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Top News", fontWeight = FontWeight.SemiBold)
        LazyColumn {
            //配列内のMockDataすべての情報を順番に表示します
            items(MockData.topNewsList) { newsData ->
                TopNewsItem(newsData = newsData, onNewsClick = {
                    // newsItemムがクリックされた場合のnavigation
                    navController.navigate("Detail/${newsData.id}")
                })
            }
        }
    }
}

@Composable
fun TopNewsItem(newsData: NewsData, onNewsClick: () -> Unit = {}) {
    //newsItemを表示するBox
    Box(modifier = Modifier
        .height(200.dp)
        .padding(8.dp)
        .clickable {
            onNewsClick()
        }) {
        Image(
            painter = painterResource(id = newsData.image), contentDescription = "",
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .padding(
                    top = 16.dp,
                    start = 16.dp
                ),
            //スペースを空けるレイアウト
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = MockData.stringToDate(newsData.publishedAt).getTimeAgo(),
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(80.dp))
            Text(
                text = newsData.title,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun TopNewsPreview(){
//    TopNewsItem(
//        newsData = NewsData(
//            2,
//            author = "Zaja Razk CNN",
//            title = "Tiger King' Joe Exotic says he has been diagnosed with aggressivedescription",
//            description = "Joseph Maldonado, known as Joe Exotic on the 2020 Netflix",
//            publishedAt = "2021 -11- 04 "
//        )
//    )
//}