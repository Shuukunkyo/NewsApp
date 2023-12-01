package com.example.newsapp

import java.net.IDN

data class NewsData(
    val id :Int,
    val author:String,
    val image:Int = R.drawable.breaking_news,
    val title:String,
    val description:String,
    val publishedAt:String,
)
