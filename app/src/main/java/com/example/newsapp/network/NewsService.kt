package com.example.newsapp.network

import com.example.newsapp.models.TopNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    //定义一个名为 getTopArticles 的方法,使用 HTTP GET 请求从指定的路径（"top-headlines"）获取头条新闻。
    //返回一个 Call<TopNewsResponse> 对象，其中 TopNewsResponse 是表示服务器响应的数据模型。
    //Call 是 Retrofit 库中的一个类，用于表示一次网络请求。

    //总体而言，这接口定义了一个获取头条新闻的网络请求方法，
    //其中包括国家和 API 密钥作为查询参数，并且期望服务器响应的数据格式符合 TopNewsResponse 类型。
    @GET("top-headlines")
    fun getTopArticles(@Query("country") country:String
                       ): Call<TopNewsResponse>

    @GET("top-headlines")
    fun getArticlesByCategory(@Query("category") category:String
                        ):Call<TopNewsResponse>

    @GET("everything")
    fun getArticlesBySources(@Query("sources")source:String
                        ):Call<TopNewsResponse>

}