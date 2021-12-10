package com.savinoordine.foodzappcompose.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.savinoordine.foodzappcompose.model.response.MealResponse

@Composable
fun MealDetailsScreen(meal: MealResponse?) {
    Column {
        Row {
            Card(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = rememberImagePainter(data = meal?.imageUrl,
                        builder = {
                            transformations(CircleCropTransformation())
                        }),
                    contentDescription = null,
                    modifier = Modifier.size(200.dp)
                )
                Text(text = meal?.name ?: "Default")
            }
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
            }) {
            Text(text = "Change state meal profile")
        }
    }
}

@Preview
@Composable
fun DetailPreview() {
    MealDetailsScreen(meal = MealResponse("", "name", "", "description"))
}