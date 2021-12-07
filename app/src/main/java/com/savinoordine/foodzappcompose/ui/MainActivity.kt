package com.savinoordine.foodzappcompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.savinoordine.foodzappcompose.ui.meals.MealsCategoryScreen
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