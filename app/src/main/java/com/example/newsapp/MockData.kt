package com.example.newsapp

import android.os.Build
import android.util.Log
import java.text.SimpleDateFormat
import java.time.Year
import java.util.Calendar
import java.util.Date
import java.util.Locale

object MockData {
    val topNewsList = listOf<NewsData>(
        NewsData(
            1,
            image = R.drawable.breaking_news,
            author ="Raja Razek CNN",
            title = "Tiger King' Joe Exotic says he has been diagnosed with aggressivedescription",
            description = "Joseph Maldonado, known as Joe Exotic on the 2020 Netflix",
            publishedAt = "2023-12-01T05:35:21Z"
        ),
         NewsData(
             2,
             image = R.drawable.mac_news,
        author ="Zaja Razk CNN",
        title = "Tiger King' Joe Exotic says he has been diagnosed with aggressivedescription",
        description = "Joseph Maldonado, known as Joe Exotic on the 2020 Netflix",
        publishedAt = "2021-11-04T05:35:21Z"
    ),
        NewsData(
            3,
            image = R.drawable.messi,
            author ="Zaja Razk CNN",
            title = "Tiger King' Joe Exotic says he has been diagnosed with aggressivedescription,Tiger King' Joe Exotic says he has been diagnosed with aggressivedescription,Tiger King' Joe Exotic says he has been diagnosed with aggressivedescription,Tiger King' Joe Exotic says he has been diagnosed with aggressivedescription,Tiger King' Joe Exotic says he has been diagnosed with aggressivedescription,Tiger King' Joe Exotic says he has been diagnosed with aggressivedescription,Tiger King' Joe Exotic says he has been diagnosed with aggressivedescription,Tiger King' Joe Exotic says he has been diagnosed with aggressivedescription",
            description = "Joseph Maldonado, known as Joe Exotic on the 2020 Netflix",
            publishedAt = "2021-11-04T05:35:21Z"
        ),
        NewsData(
            4,
            image = R.drawable.iwatch,
            author ="Zaja Razk CNN",
            title = "Tiger King' Joe Exotic says he has been diagnosed with aggressivedescription",
            description = "Joseph Maldonado, known as Joe Exotic on the 2020 Netflix",
            publishedAt = "2021-11-04T05:35:21Z"
        ),
    )
    fun getNews(newsId:Int?):NewsData{
        return topNewsList.first { it.id == newsId }
    }

    fun Date.getTimeAgo(): String {
        val calendar = Calendar.getInstance()
        calendar.time = this

        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val currentCalendar = Calendar.getInstance()
        val currentYear = currentCalendar.get(Calendar.YEAR)
        val currentMonth = currentCalendar.get(Calendar.MONTH)
        val currentDay = currentCalendar.get(Calendar.DAY_OF_MONTH)
        val currentHour = currentCalendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = currentCalendar.get(Calendar.MINUTE)

        return when {
            year < currentYear -> {
                val interval = currentYear - year
                if (interval == 1) "$interval year ago" else "$interval years ago"
            }
            month < currentMonth -> {
                val interval = currentMonth - month
                if (interval == 1) "$interval month ago" else "$interval months ago"
            }
            day < currentDay -> {
                val interval = currentDay - day
                if (interval == 1) "$interval day ago" else "$interval days ago"
            }
            hour < currentHour -> {
                val interval = currentHour - hour
                if (interval == 1) "$interval hour ago" else "$interval hours ago"
            }
            minute < currentMinute -> {
                val interval = currentMinute - minute
                if (interval == 1) "$interval minute ago" else "$interval minutes ago"
            }
            else -> "a moment ago"
        }
    }


    fun stringToDate(publishedAt:String):Date{
        val date =
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX",
                    Locale.ENGLISH).parse(publishedAt)
            }else{
                java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX",
                    Locale.ENGLISH).parse(publishedAt)
            }
        Log.d("published","$date")
        return date
    }
}