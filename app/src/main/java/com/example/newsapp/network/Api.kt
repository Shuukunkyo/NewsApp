package com.example.newsapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Api {
    public const val API_KEY="5dc65e598bdf41329ce058ec97c77935"
    private const val BASE_URL = "https://newsapi.org/v2/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val logging = HttpLoggingInterceptor()

    //Http Clientを作成
    val httpClient = OkHttpClient.Builder().apply {
        //インターセプターを追加、添加拦截器
        addInterceptor(
            Interceptor{
                chain ->
                val builder = chain.request().newBuilder()
                //两个parameter key 跟value
                builder.header("X-Api-key", API_KEY)
                //返回拦截器
                return@Interceptor chain.proceed(builder.build())
            }
        )
        //设置为http日志拦截器级别的主体
        logging.level=HttpLoggingInterceptor.Level.BODY
        //添加名为logging的网络拦截器
        addNetworkInterceptor(logging)
    }.build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .client(httpClient)
        .build()

    val retrofitService : NewsService by lazy {
        retrofit.create(NewsService::class.java)
    }
}