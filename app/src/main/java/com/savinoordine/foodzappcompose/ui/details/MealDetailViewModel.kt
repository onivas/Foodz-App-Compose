package com.savinoordine.foodzappcompose.ui.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.savinoordine.foodzappcompose.model.MealsRepository
import com.savinoordine.foodzappcompose.model.response.MealResponse

class MealDetailViewModel
constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val mealsRepository: MealsRepository = MealsRepository.getInstance()
    val mealState = mutableStateOf<MealResponse?>(null)

    init {
        val mealId = savedStateHandle.get<String>("meal_category_id") ?: ""

        mealState.value = mealsRepository.getMeal(mealId)
    }
}