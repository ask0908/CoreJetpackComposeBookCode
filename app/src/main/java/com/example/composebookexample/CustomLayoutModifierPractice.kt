package com.example.composebookexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebookexample.ui.theme.ComposeBookExampleTheme
import kotlin.math.roundToInt

class CustomLayoutModifierPractice : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBookExampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CustomLayoutMainScreen()
                }
            }
        }
    }
}

fun Modifier.exampleLayout(
    fraction: Float
) = layout { measurable, constraints ->
    val placeable = measurable.measure(constraints = constraints)
    val firstBaseline = placeable[FirstBaseline]
    val lastBaseline = placeable[LastBaseline]
    if (placeable[FirstBaseline] == AlignmentLine.Unspecified) {
        // Modifier에 전달된 자식은 FirstBaseline 정렬을 지원하지 않음
    }

    val x = -(placeable.width * fraction).roundToInt()

    layout(placeable.width, placeable.height) {
        placeable.placeRelative(x = x, y = 0)
    }
}

@Composable
fun CustomLayoutMainScreen() {
    Box(
        modifier = Modifier.size(120.dp, 80.dp),
        contentAlignment = Alignment.Center
    ) {
        Column {
            ColorBox(
                modifier = Modifier.exampleLayout(0f).background(Color.Blue)
            )
            ColorBox(
                modifier = Modifier.exampleLayout(.25f).background(Color.Green)
            )
            ColorBox(
                modifier = Modifier.exampleLayout(.5f).background(Color.Yellow)
            )
            ColorBox(
                modifier = Modifier.exampleLayout(.25f).background(Color.Red)
            )
            ColorBox(
                modifier = Modifier.exampleLayout(0f).background(Color.Magenta)
            )
        }
    }
}

@Composable
fun ColorBox(modifier: Modifier) {
    Box(
        modifier = Modifier
            .padding(1.dp)
            .size(width = 50.dp, height = 10.dp)
            .then(modifier)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview7() {
    ComposeBookExampleTheme {
        CustomLayoutMainScreen()
    }
}