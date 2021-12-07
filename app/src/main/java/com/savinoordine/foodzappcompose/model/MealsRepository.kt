package com.savinoordine.foodzappcompose.model

import com.savinoordine.foodzappcompose.api.MealsApiImpl
import com.savinoordine.foodzappcompose.model.response.MealsCategoryResponse

class MealsRepository
constructor(private val apiService: MealsApiImpl = MealsApiImpl()) {

    // no suspending request made yet
    suspend fun getMeals(): MealsCategoryResponse {
        return apiService.fetchMeals()
    }
}