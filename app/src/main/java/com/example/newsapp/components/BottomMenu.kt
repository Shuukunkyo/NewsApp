package com.example.newsapp.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.newsapp.BottomMenuScreen
import com.example.newsapp.R

@Composable
fun BottomMenu(navController: NavController){
    //BottomMenu　navigationの各項目
    val menuItems = listOf(
        BottomMenuScreen.TopNews,
        BottomMenuScreen.Categories,
        BottomMenuScreen.Sources)

    //navigation各項目の構築
    BottomNavigation(contentColor = colorResource(id = (R.color.white))) {
        // 現在のバックスタックエントリーを取得
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        // 現在のルートを取得
        val currentRoute = navBackStackEntry?.destination?.route
        // メニューアイテムのリストを順に処理
        menuItems.forEach{
            BottomNavigationItem(
                // ラベルとしてアイテムのタイトルを表示
                label={ Text(text = it.title)},
                alwaysShowLabel=true,
                // 選択されたアイテムのテキストカラーを白に設定
                selectedContentColor= Color.White,
                // 選択されていないアイテムのテキストカラーをグレーに設定
                unselectedContentColor= Color.Gray,
                // 現在のルートがアイテムのルートと一致するかどうかで選択状態を設定
                selected = currentRoute == it.route,
                // アイテムがクリックされたときの処理
                onClick = {
                          navController.navigate(it.route){
                              navController.graph.startDestinationRoute?.let {
                                  route ->
                                  popUpTo(route){
                                      saveState = true
                                  }
                              }
                              launchSingleTop = true
                              restoreState = true
                          }
                },
                icon = { Icon(imageVector = it.icon, contentDescription = it.title) }
            )
        }
    }
}