package com.example.composebookexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.composebookexample.flow.FlowDemoViewModel
import com.example.composebookexample.ui.theme.ComposeBookExampleTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis

class FlowActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBookExampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ScreenSetup3(viewModel())
                }
            }
        }
    }
}

@Composable
fun MainScreen12(viewModel: FlowDemoViewModel) {
    val count by viewModel.sharedFlow.collectAsState(initial = 0)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = count.toString(),
            style = TextStyle(fontSize = 40.sp)
        )
        Button(onClick = { viewModel.startSharedFlow() }) {
            Text("Click me")
        }
    }
}

@Composable
fun ScreenSetup3(viewModel: FlowDemoViewModel = viewModel()) {
    MainScreen12(viewModel)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview20() {
    ComposeBookExampleTheme {
        ScreenSetup3(viewModel())
    }
}