package com.savinoordine.foodzappcompose.ui.details

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.savinoordine.foodzappcompose.model.response.MealResponse
import java.lang.Float.min

@Composable
fun MealDetailsScreen(meal: MealResponse?) {
//    var imageState by remember { mutableStateOf(MealDetailImageState.Normal) }
//    val transition = updateTransition(targetState = imageState, label = "")
//
//    val imageDpState by transition.animateDp(targetValueByState = { it.size }, label = "")
//    val buttonColorState by transition.animateColor(targetValueByState = { it.color }, label = "")


    val scrollState = rememberLazyListState()
    val toolbarOffset = min(
        1F,
        1 - (scrollState.firstVisibleItemScrollOffset / 600F + scrollState.firstVisibleItemIndex)
    )
    val imageSize by animateDpAsState(targetValue = max(100.dp, 140.dp * toolbarOffset))

    Surface {
        Column {
            Surface(elevation = 4.dp) {
                Row(modifier = Modifier.fillMaxWidth()) {
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
                            modifier = Modifier.size(imageSize)
                        )
                        Text(
                            text = meal?.name ?: "Default",
                            modifier = Modifier.padding(16.dp),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }

//            Button(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                colors = ButtonDefaults.buttonColors(backgroundColor = buttonColorState),
//                onClick = {
//                    imageState =
//                        if (imageState == MealDetailImageState.Normal) MealDetailImageState.Expanded
//                        else MealDetailImageState.Normal
//
//                }) {
//                Text(
//                    text = "Change state meal profile",
//                    modifier = Modifier.padding(16.dp),
//                    textAlign = TextAlign.Center,
//                )
//            }


            val dummies = (0..100).map { it.toString() }
            LazyColumn(state = scrollState, modifier = Modifier.fillMaxWidth()) {
                items(dummies) { item ->
                    Text(text = item, modifier = Modifier.padding(8.dp))
                }
            }
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