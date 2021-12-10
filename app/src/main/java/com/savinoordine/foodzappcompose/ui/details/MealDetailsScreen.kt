package com.savinoordine.foodzappcompose.ui.details

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.savinoordine.foodzappcompose.model.response.MealResponse

@Composable
fun MealDetailsScreen(meal: MealResponse?) {
    var imageState by remember { mutableStateOf(MealDetailImageState.Normal) }
    val transition = updateTransition(targetState = imageState, label = "")

    val imageDpState by transition.animateDp(targetValueByState = { it.size }, label = "")
    val buttonColorState by transition.animateColor(targetValueByState = { it.color }, label = "")

    Column {
        Row {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = 8.dp,
            ) {
                Image(
                    painter = rememberImagePainter(data = meal?.imageUrl,
                        builder = {
                            transformations(CircleCropTransformation())
                        }),
                    contentDescription = null,
                    modifier = Modifier.size(imageDpState)
                )
                Text(
                    text = meal?.name ?: "Default",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                )
            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = buttonColorState),
            onClick = {
                imageState =
                    if (imageState == MealDetailImageState.Normal) MealDetailImageState.Expanded
                    else MealDetailImageState.Normal

            }) {
            Text(
                text = "Change state meal profile",
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
}

enum class MealDetailImageState(val size: Dp, val color: Color) {
    Normal(size = 100.dp, color = Color.Magenta),
    Expanded(size = 200.dp, color = Color.Red)
}

@Preview
@Composable
fun DetailPreview() {
    MealDetailsScreen(meal = MealResponse("", "name", "", "description"))
}