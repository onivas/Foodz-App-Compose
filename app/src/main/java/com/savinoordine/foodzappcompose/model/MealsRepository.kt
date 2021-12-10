package com.savinoordine.foodzappcompose.model

import com.savinoordine.foodzappcompose.api.MealsApiImpl
import com.savinoordine.foodzappcompose.model.response.MealResponse
import com.savinoordine.foodzappcompose.model.response.MealsCategoryResponse

class MealsRepository
constructor(private val apiService: MealsApiImpl = MealsApiImpl()) {

    private var cacheMeals = listOf<MealResponse>()

    suspend fun getMeals(): MealsCategoryResponse {
        val response = apiService.fetchMeals()
        cacheMeals = response.categories
        return response
    }

    fun getMeal(mealId: String): MealResponse? {
        return cacheMeals.firstOrNull { it.id == mealId }
    }

    companion object {
        @Volatile
        private var instance: MealsRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: MealsRepository().also { instance = it }
        }
    }
}