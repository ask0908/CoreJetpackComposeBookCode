package com.example.composebookexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebookexample.ui.theme.ComposeBookExampleTheme

class IntrinsicSizeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBookExampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen4()
                }
            }
        }
    }
}

@Composable
fun MainScreen4() {
    var textState by remember { mutableStateOf("") }
    val onTextChange = { text: String ->
        textState = text
    }
    Column(
        modifier = Modifier
            .width(200.dp)
            .padding(5.dp)
    ) {
        Column(modifier = Modifier.width(IntrinsicSize.Min)) {
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = textState
            )
            Box(
                modifier = Modifier
                    .height(10.dp)
                    .fillMaxWidth()
                    .background(Color.Blue)
            )
        }
        MyTextField2(text = textState, onTextChange = onTextChange)
    }
}

@Composable
fun MyTextField2(
    text: String,
    onTextChange: (String) -> Unit
) {
    TextField(value = text, onValueChange = onTextChange)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview10() {
    ComposeBookExampleTheme {
        MainScreen4()
    }
}