package com.savinoordine.foodzappcompose.ui.meals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.savinoordine.foodzappcompose.ui.theme.FoodzAppComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodzAppComposeTheme {
                MealsCategoryScreen()
            }
        }
    }
}

@Composable
fun MealsCategoryScreen() {
    val viewModel: MealsCategoryViewModel = viewModel()
    val meals = viewModel.fetchMeals()
    Text(text = "Hello")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FoodzAppComposeTheme {
        MealsCategoryScreen()
    }
}