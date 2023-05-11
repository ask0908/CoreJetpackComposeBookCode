package com.example.composebookexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composebookexample.ui.theme.ComposeBookExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBookExampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                }
            }
        }
    }
}

@Composable
fun DemoScreen() {
    var textState by remember { mutableStateOf("") }
    val onTextChange = { text: String ->
        textState = text
    }
    MyTextField(
        text = textState,
        onTextChange = onTextChange
    )
}

@Composable
fun MyTextField(
    text: String,
    onTextChange: (String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = onTextChange
    )
}

@Composable
fun FunctionA() {
    var switchState by remember { mutableStateOf(true) }

    // onSwitchChange 호출 시 boolean 파라미터를 받아서 상태(switchState)에 할당한다
    val onSwitchChange = { value: Boolean ->
        switchState = value
    }

    FunctionB(
        switchState = switchState,
        onSwitchChange = onSwitchChange
    )
}

@Composable
fun FunctionB(
    switchState: Boolean,
    onSwitchChange: (Boolean) -> Unit
) {
    Switch(
        checked = switchState,
        onCheckedChange = onSwitchChange
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeBookExampleTheme {
    }
}