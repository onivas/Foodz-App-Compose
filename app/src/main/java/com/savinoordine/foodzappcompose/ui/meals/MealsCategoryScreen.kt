package com.savinoordine.foodzappcompose.ui.meals

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.savinoordine.foodzappcompose.model.response.MealResponse
import com.savinoordine.foodzappcompose.ui.theme.FoodzAppComposeTheme


@Composable
fun MealsCategoryScreen(navigationCallback: (String) -> Unit) {
    val viewModel: MealsCategoryViewModel = viewModel()
    val meals = viewModel.mealsState.value

    LazyColumn(contentPadding = PaddingValues(8.dp)) {
        items(meals) { meal ->
            MealCategory(meal, navigationCallback)
        }
    }
}

@Composable
fun MealCategory(meal: MealResponse, navigationCallback: (String) -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.CenterVertically)
            .padding(4.dp)
            .clickable {
                navigationCallback(meal.id)
            },
        elevation = 4.dp,
    ) {
        Row(
            modifier = Modifier.animateContentSize()
        ) {

            Image(
                painter = rememberImagePainter(meal.imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(88.dp)
                    .padding(4.dp)
                    .align(Alignment.CenterVertically)
            )

            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth(0.8F)
            ) {
                Text(
                    text = meal.name,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = meal.description,
                    style = MaterialTheme.typography.subtitle2,
                    textAlign = TextAlign.Start,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = if (isExpanded) 10 else 4,
                )
            }
            Icon(
                imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.Top)
                    .clickable { isExpanded = !isExpanded },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FoodzAppComposeTheme {
        MealCategory(MealResponse("", "name", "", "description")) {}
    }
}