package com.savinoordine.foodzappcompose.api

import com.savinoordine.foodzappcompose.model.response.MealsCategoryResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MealsApiImpl {

    private val apiService: MealsApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(MealsApi::class.java)
    }

    suspend fun fetchMeals(): MealsCategoryResponse {
        return apiService.fetchMeals()
    }

    interface MealsApi {
        @GET("categories.php")
        suspend fun fetchMeals(): MealsCategoryResponse
    }
}