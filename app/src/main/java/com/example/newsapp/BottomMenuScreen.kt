package com.example.newsapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

// 底部メニューの各項目を表す封じられたクラス
sealed class BottomMenuScreen(val route : String,
    val icon:ImageVector,val title:String){

    object TopNews:BottomMenuScreen("top",
        icon = Icons.Outlined.Home,"Top News")
    object Categories:BottomMenuScreen("categories",
        icon = Icons.Outlined.Home,"Categories")
    object Sources:BottomMenuScreen("sources",
        icon = Icons.Outlined.Home,"Sources")



}
