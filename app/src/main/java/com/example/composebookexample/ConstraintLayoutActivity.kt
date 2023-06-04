package com.example.composebookexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.composebookexample.ui.theme.ComposeBookExampleTheme

class ConstraintLayoutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBookExampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen3()
                }
            }
        }
    }
}

@Composable
fun MyButton(
    text: String,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {},
        modifier = modifier
    ) {
        Text(text)
    }
}

@Composable
fun MainScreen3() {
    ConstraintLayout(
        myConstraintSet(margin = 8.dp),
        modifier = Modifier.size(
            width = 200.dp,
            height = 200.dp
        )
    ) {
        MyButton(text = "Button1", Modifier.size(200.dp).layoutId("button1"))
    }
}

private fun myConstraintSet(margin: Dp): ConstraintSet = ConstraintSet {
    val button1 = createRefFor("button1")
    constrain(button1) {
        linkTo(parent.top, parent.bottom, topMargin = margin, bottomMargin = margin)
        linkTo(parent.start, parent.end, startMargin = margin, endMargin = margin)
        width = Dimension.fillToConstraints
        height = Dimension.fillToConstraints
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview9() {
    ComposeBookExampleTheme {
        MainScreen3()
    }
}