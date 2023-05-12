package com.example.composebookexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebookexample.ui.theme.ComposeBookExampleTheme

class ModifierPractice : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBookExampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ModifierDemoScreen()
                }
            }
        }
    }
}

@Composable
fun ModifierDemoScreen() {
    val modifier = Modifier
        .border(width = 2.dp, color = Color.Black)
        .padding(all = 10.dp)

    val modifier2 = Modifier.height(100.dp)

    Column(
        modifier = Modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Hello Compose",
            modifier = modifier.then(modifier2), // modifier 여러 개를 합칠 땐 then() 사용
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomImage(
            image = R.drawable.vacation,
            modifier = Modifier.padding(16.dp)
                .width(270.dp)
                .clip(shape = RoundedCornerShape(30.dp))
        )
    }
}

// modifier 매개변수는 반드시 선택적이어야 하고 없어도 호출할 수 있어야 해서 기본값인 빈 인스턴스로 초기화해 둠
@Composable
fun CustomImage(image: Int, modifier: Modifier = Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(id = image),
        contentDescription = null
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    ComposeBookExampleTheme {
        ModifierDemoScreen()
    }
}