package com.example.composebookexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.composebookexample.ui.theme.ComposeBookExampleTheme
import kotlin.math.roundToInt

class GestureActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBookExampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen10()
                }
            }
        }
    }
}

@Composable
fun MainScreen10() {
    RotateDemo()
}

@Composable
fun ClickDemo() {
    var colorState by remember { mutableStateOf(true) }
    var bgColor by remember { mutableStateOf(Color.Blue) }

    val clickHandler = {
        colorState = !colorState
        bgColor = if (colorState) {
            Color.Blue
        } else {
            Color.DarkGray
        }
    }

    Box(modifier = Modifier
        .clickable { clickHandler() }
        .background(bgColor)
        .size(100.dp))
}

@Composable
fun TabPressDemo() {
    var textState by remember { mutableStateOf("Waiting...") }
    var tapHandler = { status: String ->
        textState = status
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier
            .padding(10.dp)
            .background(Color.Blue)
            .size(100.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = { tapHandler("onPress 감지") },
                    onDoubleTap = { tapHandler("onDoubleTap 감지") },
                    onLongPress = { tapHandler("onLongPress 감지") },
                    onTap = { tapHandler("onTap 감지") }
                )
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(textState)
    }
}

@Composable
fun DragDemo() {
    Box(modifier = Modifier.fillMaxSize()) {
        var xOffset by remember { mutableStateOf(0f) }

        Box(
            modifier = Modifier
                .offset { IntOffset(xOffset.roundToInt(), 0) }
                .size(100.dp)
                .background(Color.Blue)
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { distance ->
                        xOffset += distance
                    }
                )
        )
    }
}

@Composable
fun PointerInputDrag() {
    Box(modifier = Modifier.fillMaxSize()) {
        var xOffset by remember { mutableStateOf(0f) }
        var yOffset by remember { mutableStateOf(0f) }

        Box(modifier = Modifier
            .offset { IntOffset(xOffset.roundToInt(), yOffset.roundToInt()) }
            .background(Color.Blue)
            .size(100.dp)
            .pointerInput(Unit) {
                detectDragGestures { _, distances ->
                    xOffset += distances.x
                    yOffset += distances.y
                }
            }
        )
    }
}

@Composable
fun ScrollableModifier() {
    var offset by remember { mutableStateOf(0f) }

    Box(modifier = Modifier
        .fillMaxSize()
        .scrollable(
            orientation = Orientation.Vertical, state = rememberScrollableState { distance ->
                offset += distance
                distance
            }
        )
    ) {
        Box(modifier = Modifier
            .size(90.dp)
            .offset { IntOffset(0, offset.roundToInt()) }
            .background(Color.Red)
        )
    }
}

@Composable
fun ScrollModifiers() {
    val image = ImageBitmap.imageResource(id = R.drawable.vacation)

    Box(
        modifier = Modifier
            .size(150.dp)
            .verticalScroll(rememberScrollState())
            .horizontalScroll(rememberScrollState())
    ) {
        Canvas(modifier = Modifier.size(360.dp, 270.dp), onDraw = {
            drawImage(
                image = image,
                topLeft = Offset(x = 0f, y = 0f)
            )
        })
    }
}

@Composable
fun MultiTouchDemo() {
    var scale by remember { mutableStateOf(1f) }
    val state = rememberTransformableState { scaleChange, offsetChange, rotationChange ->
        scale *= scaleChange
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .graphicsLayer(scaleX = scale, scaleY = scale)
                .transformable(state = state)
                .background(Color.Blue)
                .size(100.dp)
        )
    }
}

@Composable
fun RotateDemo() {
    var scale by remember { mutableStateOf(1f) }
    var angle by remember { mutableStateOf(0f) } // 추가
    val state = rememberTransformableState { scaleChange, offsetChange, rotationChange ->
        scale *= scaleChange
        angle += rotationChange // 추가
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .graphicsLayer(scaleX = scale, scaleY = scale, rotationZ = angle) // rotationZ 추가
                .transformable(state = state)
                .background(Color.Blue)
                .size(100.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview18() {
    ComposeBookExampleTheme {
        MainScreen10()
    }
}