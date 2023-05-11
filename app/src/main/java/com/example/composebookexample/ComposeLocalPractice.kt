package com.example.composebookexample

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebookexample.ui.theme.ComposeBookExampleTheme

class ComposeLocalPractice : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBookExampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Composable1()
                }
            }
        }
    }
}

private val LocalColor = staticCompositionLocalOf { Color(0xFFffdbcf) }

@Composable
fun Composable1() {
    val color = if (isSystemInDarkTheme()) {
        Color(0xFFa08d87)
    } else {
        Color(0xFFffdbcf)
    }

    Column {
        Composable2()
        CompositionLocalProvider(LocalColor provides color) {
            Composable3()
        }
    }
}

@Composable
fun Composable2() {
    Composable4()
}

@Composable
fun Composable3() {
    Text("Composable 3", modifier = Modifier.background(LocalColor.current))

    CompositionLocalProvider(LocalColor provides Color.Red) {
        Composable5()
    }
}

@Composable
fun Composable4() {
    Composable6()
}

@Composable
fun Composable5() {
    Text("Composable 5", modifier = Modifier.background(LocalColor.current))

    CompositionLocalProvider(LocalColor provides Color.Green) {
        Composable7()
    }

    CompositionLocalProvider(LocalColor provides Color.Yellow) {
        Composable8()
    }
}

@Composable
fun Composable6() {
    Text("Composable 6", modifier = Modifier.background(LocalColor.current))
}

@Composable
fun Composable7() {
    Text("Composable 7", modifier = Modifier.background(LocalColor.current))
}

@Composable
fun Composable8() {
    Text("Composable 8", modifier = Modifier.background(LocalColor.current))
}

@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun DarkPreview() {
    ComposeBookExampleTheme {
        Composable1()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    ComposeBookExampleTheme {
        Composable1()
    }
}