package com.savinoordine.foodzappcompose.ui.meals

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.savinoordine.foodzappcompose.model.MealsRepository
import com.savinoordine.foodzappcompose.model.response.MealResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MealsCategoryViewModel
constructor(private val mealsRepository: MealsRepository = MealsRepository()) : ViewModel() {

    val mealsState: MutableState<List<MealResponse>> = mutableStateOf(emptyList())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val meals = fetchMeals()
            mealsState.value = meals

        }
    }

    private suspend fun fetchMeals(): List<MealResponse> {
        return mealsRepository.getMeals().categories
    }
}