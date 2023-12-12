package com.example.newsapp.network

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.newsapp.models.ArticleCategory
import com.example.newsapp.models.TopNewsResponse
import com.example.newsapp.models.getAllArticleCategory
import com.example.newsapp.models.getArticleCategory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsManager {

    private val _newsResponse = mutableStateOf(TopNewsResponse())
    val newsResponse : State<TopNewsResponse>
        @Composable get() = remember{
            _newsResponse
        }

    val sourceName = mutableStateOf("abc-news")

    private val _getArticleByCategory = mutableStateOf(TopNewsResponse())
    val getArticleByCategory : MutableState<TopNewsResponse>
        @Composable get() = remember{
            _getArticleByCategory
        }

    private val _getArticleBySource = mutableStateOf(TopNewsResponse())
    val getArticleBySource : MutableState<TopNewsResponse>
        @Composable get() = remember{
            _getArticleBySource
        }


    val selectedCategory : MutableState<ArticleCategory?> = mutableStateOf(null)

    init {
        getArticles()
    }

    private fun getArticles(){
        val service = Api.retrofitService.getTopArticles("jp")
        service.enqueue(object: Callback<TopNewsResponse> {
            override fun onResponse(
                call: Call<TopNewsResponse>,
                response: Response<TopNewsResponse>
            ) {
                if(response.isSuccessful){
                    _newsResponse.value = response.body()!!
                    Log.d("news","${_newsResponse.value}")
                }else{
                    Log.d("error","${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<TopNewsResponse>, t: Throwable) {
                Log.d("error","${t.printStackTrace()}")
            }
        })
    }

    fun getArticlesByCategory(category: String){
        val service = Api.retrofitService.getArticlesByCategory(category)
        service.enqueue(object: Callback<TopNewsResponse> {
            override fun onResponse(
                call: Call<TopNewsResponse>,
                response: Response<TopNewsResponse>
            ) {
                if(response.isSuccessful){
                    _getArticleByCategory.value = response.body()!!
                    Log.d("category","${_getArticleByCategory.value}")
                }else{
                    Log.d("error","${response.code()}")
                }
            }

            override fun onFailure(call: Call<TopNewsResponse>, t: Throwable) {
                Log.d("error","${t.message}")
            }
        })
    }

    fun getArticlesBySource(){
        val service = Api.retrofitService.getArticlesBySources(sourceName.value)
        service.enqueue(object: Callback<TopNewsResponse> {
            override fun onResponse(
                call: Call<TopNewsResponse>,
                response: Response<TopNewsResponse>
            ) {
                if(response.isSuccessful){
                    _getArticleBySource.value = response.body()!!
                    Log.d("source","${_getArticleBySource.value}")
                }else{
                    Log.d("error","${response.code()}")
                }
            }

            override fun onFailure(call: Call<TopNewsResponse>, t: Throwable) {
                Log.d("error","${t.message}")
            }
        })
    }




    fun onSelectedCategoryChanged(category:String){
        val newCategory = getArticleCategory(category = category)
        selectedCategory.value = newCategory
    }
}