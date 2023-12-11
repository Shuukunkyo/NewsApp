package com.example.newsapp.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapp.BottomMenuScreen
import com.example.newsapp.components.BottomMenu
import com.example.newsapp.models.TopNewsArticle
import com.example.newsapp.network.NewsManager
import com.example.newsapp.ui.screen.Categories
import com.example.newsapp.ui.screen.DetailScreen
import com.example.newsapp.ui.screen.Sources
import com.example.newsapp.ui.screen.TopNews

@Composable
fun NewsApp(){
    // スクロールを制御するための ScrollState を作成
    val scrollState= rememberScrollState()
    // ナビゲーションコントローラーを作成
    val navController= rememberNavController()
    // メイン画面
    MainScreen(navController = navController,scrollState)
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavHostController,scrollState: ScrollState){
    // Scaffold を使用してアプリのメイン画面を作成
    Scaffold(
        bottomBar = {
            // bottomNavigation
            BottomMenu(navController = navController)
        },
    ) {
        // メインのコンテンツ領域(Navigation制御を含む)
        Navigation(navController=navController,
                    scrollState = scrollState,
                    paddingValues = it)
    }
}

@Composable
fun Navigation(navController: NavHostController,
               scrollState: ScrollState,
               newsManager: NewsManager = NewsManager(),
               paddingValues: PaddingValues
){
    val articles = newsManager.newsResponse.value.articles
    Log.d("news","$articles")
    articles?.let {
        NavHost(navController = navController,
                startDestination = BottomMenuScreen.TopNews.route,
                modifier = Modifier.padding(paddingValues=paddingValues)
        ){
            // bottomNavigationに関連する composable 関数の設定
            bottomNavigation(navController = navController,articles,newsManager)
            // news詳細ページの composable 関数の設定、(渡されたパラメータ newsId を含む)
            composable("Detail/{index}",
                arguments = listOf(navArgument("index"){type= NavType.IntType})
            ){
                    navBackStackEntry ->
                // Navigationパラメータから newsId を取得し、その情報を使用してニュース詳細ページをレンダリング
                val index = navBackStackEntry.arguments?.getInt("index")
                index?.let {
                    val article = articles[index]
                    DetailScreen(article,scrollState,navController)
                }
            }
        }
    }

    // NavHostを作成し、開始目的地を "TopNews" に設定


}

// NavGraphBuilderにbottomNavigationに関する composable 関数を追加
fun NavGraphBuilder.bottomNavigation(navController: NavHostController,
                                     articles:List<TopNewsArticle>,
                                     newsManager: NewsManager){
    composable(BottomMenuScreen.TopNews.route){
        TopNews(navController = navController,articles)
    }
    composable(BottomMenuScreen.Categories.route){
        newsManager.getArticlesByCategory("business")
        newsManager.onSelectedCategoryChanged("business")


        Categories(newsManager = newsManager, onFetchCategory = {
            newsManager.onSelectedCategoryChanged(it)
            newsManager.getArticlesByCategory(it)
        })
    }
    composable(BottomMenuScreen.Sources.route){
        Sources()
    }
}