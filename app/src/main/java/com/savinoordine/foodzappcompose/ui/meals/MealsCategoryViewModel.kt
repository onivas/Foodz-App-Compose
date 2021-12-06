package com.savinoordine.foodzappcompose.ui.meals

import androidx.lifecycle.ViewModel
import com.savinoordine.foodzappcompose.model.MealsRepository
import com.savinoordine.foodzappcompose.model.response.MealResponse

class MealsCategoryViewModel
constructor(private val mealsRepository: MealsRepository = MealsRepository()) : ViewModel() {

    fun fetchMeals(): List<MealResponse> {
        return mealsRepository.getMeals()?.categories.orEmpty()
    }
}