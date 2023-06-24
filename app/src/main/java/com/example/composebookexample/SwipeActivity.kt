package com.example.composebookexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebookexample.ui.theme.ComposeBookExampleTheme
import kotlin.math.roundToInt

class SwipeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBookExampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen11()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen11() {
    val parentBoxWidth = 320.dp
    val childBoxSides = 30.dp
    val swipeableState = rememberSwipeableState(initialValue = "L")
    val widthPx = with(LocalDensity.current) {
        (parentBoxWidth - childBoxSides).toPx()
    }
    val anchors = mapOf(0f to "L", widthPx / 2 to "C", widthPx to "R")

    Box {
        Box(
            modifier = Modifier
                .padding(20.dp)
                .width(parentBoxWidth)
                .height(childBoxSides)
                .swipeable(
                    state = swipeableState,
                    anchors = anchors,
                    thresholds = { _, _ -> FractionalThreshold(.5f) },
                    orientation = Orientation.Horizontal
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(5.dp)
                    .background(Color.DarkGray)
                    .align(Alignment.CenterStart)
            )
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .background(Color.DarkGray, shape = CircleShape)
                    .align(Alignment.CenterStart)
            )
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .background(Color.DarkGray, shape = CircleShape)
                    .align(Alignment.Center)
            )
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .background(Color.DarkGray, shape = CircleShape)
                    .align(Alignment.CenterEnd)
            )

            // 자식 Box
            Box(
                modifier = Modifier
                    .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
                    .size(childBoxSides)
                    .background(Color.Blue),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = swipeableState.currentValue,
                    color = Color.White,
                    fontSize = 22.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview19() {
    ComposeBookExampleTheme {
        MainScreen11()
    }
}