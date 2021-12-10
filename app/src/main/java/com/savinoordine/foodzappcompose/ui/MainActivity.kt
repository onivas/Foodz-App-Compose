package com.savinoordine.foodzappcompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavArgs
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.savinoordine.foodzappcompose.ui.details.MealDetailViewModel
import com.savinoordine.foodzappcompose.ui.details.MealDetailsScreen
import com.savinoordine.foodzappcompose.ui.meals.MealsCategoryScreen
import com.savinoordine.foodzappcompose.ui.theme.FoodzAppComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodzAppComposeTheme {
                FoodzApp()
            }
        }
    }

    companion object {
        const val MEAL_LIST_ROUTE = "destination_meal_list"

        const val MEAL_DETAIL_CATEGORY_ID = "meal_category_id"
        const val MEAL_DETAIL_ROUTE = "destination_meal_detail/{$MEAL_DETAIL_CATEGORY_ID}"
    }
}

@Composable
private fun FoodzApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MainActivity.MEAL_LIST_ROUTE
    ) {
        composable(route = MainActivity.MEAL_LIST_ROUTE) {
            MealsCategoryScreen() { mealId ->
                navController.navigate("${MainActivity.MEAL_DETAIL_ROUTE}/$mealId")

            }
        }
        composable(
            route = MainActivity.MEAL_DETAIL_ROUTE,
            arguments = listOf(navArgument(MainActivity.MEAL_DETAIL_CATEGORY_ID) {
                type = NavType.StringType
            })
        ) {
            val viewModel: MealDetailViewModel = viewModel()
            MealDetailsScreen(meal = viewModel.mealState.value)
        }
    }
}


