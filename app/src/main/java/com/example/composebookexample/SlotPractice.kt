package com.example.composebookexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebookexample.ui.theme.ComposeBookExampleTheme

class SlotPractice : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBookExampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //
                }
            }
        }
    }
}

@Composable
fun SlotDemo(
    topContent: @Composable () -> Unit,
    middleContent: @Composable () -> Unit,
    bottomContent: @Composable () -> Unit
) {
    Column {
        topContent()
        middleContent()
        bottomContent()
    }
}

@Composable
fun ButtonDemo() {
    Button(onClick = {}) {
        Text("Click Me")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    ComposeBookExampleTheme {
        SlotDemo(
            topContent = { Text("Top Text") },
            middleContent = { ButtonDemo() },
            bottomContent = { Text("Bottom Text") }
        )
    }
}