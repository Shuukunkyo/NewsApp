package com.example.newsapp.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapp.BottomMenuScreen
import com.example.newsapp.MockData
import com.example.newsapp.components.BottomMenu
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
        Navigation(navController=navController, scrollState = scrollState)
    }
}

@Composable
fun Navigation(navController: NavHostController,
               scrollState: ScrollState,
               newsManager: NewsManager = NewsManager()){
    val article = newsManager.newsResponse.value.articles
    Log.d("news","$article")


    // NavHostを作成し、開始目的地を "TopNews" に設定
    NavHost(navController = navController,  startDestination = "TopNews" ){
        // bottomNavigationに関連する composable 関数の設定
        bottomNavigation(navController = navController)
        // newsListページの composable 関数の設定
        composable("TopNews"){
            TopNews(navController = navController)
        }
        // news詳細ページの composable 関数の設定、(渡されたパラメータ newsId を含む)
        composable("Detail/{newsId}",
            arguments = listOf(navArgument("newsId"){type= NavType.IntType})
            ){
            navBackStackEntry ->
            // Navigationパラメータから newsId を取得し、その情報を使用してニュース詳細ページをレンダリング
            val id = navBackStackEntry.arguments?.getInt("newsId")
            val newsData = MockData.getNews(id)
            DetailScreen(newsData,scrollState,navController)
        }
    }

}

// NavGraphBuilderにbottomNavigationに関する composable 関数を追加
fun NavGraphBuilder.bottomNavigation(navController: NavHostController){
    composable(BottomMenuScreen.TopNews.route){
        TopNews(navController = navController)
    }
    composable(BottomMenuScreen.Categories.route){
        Categories()
    }
    composable(BottomMenuScreen.Sources.route){
        Sources()
    }
}