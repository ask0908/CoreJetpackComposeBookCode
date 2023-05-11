package com.example.composebookexample

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebookexample.ui.theme.ComposeBookExampleTheme

class SlotApiTutorial : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBookExampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    /* Checkbox 컴포넌트 2개에 쓸 상태, 이벤트 핸들러 */
    var linearSelected by remember { mutableStateOf(true) }
    var imageSelected by remember { mutableStateOf(true) }

    val onLinearClick = { value: Boolean ->
        linearSelected = value
    }

    val onTitleClick = { value: Boolean ->
        imageSelected = value
    }

    // ScreenContent() 호출은 상태 변수, 이벤트 핸들러를 전달하며 시작함
    ScreenContent(
        linearSelected = linearSelected,
        imageSelected = imageSelected,
        onTitleClick = onTitleClick,
        onLinearClick = onLinearClick,
        titleContent = {
            if (imageSelected) {
                TitleImage(drawing = R.drawable.baseline_cloud_download_24)
            } else {
                Text(
                    text = "다운로드 중...",
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.padding(30.dp)
                )
            }
        },
        progressContent = {
            if (linearSelected) {
                LinearProgressIndicator(modifier = Modifier.height(40.dp))
            } else {
                CircularProgressIndicator(
                    modifier = Modifier.size(200.dp),
                    strokeWidth = 18.dp
                )
            }
        }
    )
}

/**
 * 제목, 진행 상태 인디케이터, 체크박스 표시
 */
@Composable
fun ScreenContent(
    linearSelected: Boolean,
    imageSelected: Boolean,
    onTitleClick: (Boolean) -> Unit,
    onLinearClick: (Boolean) -> Unit,
    titleContent: @Composable () -> Unit,   // 제목 인디케이터 슬롯
    progressContent: @Composable () -> Unit // 진행 상태 인디케이터 슬롯
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween // Column의 자식 컴포넌트들이 균등하게 공간을 차지하게 (0번 요소 앞, 마지막 요소 뒤의 간격은 제외)
    ) {
        titleContent()
        progressContent()
        CheckBoxes(
            linearSelected = linearSelected,
            imageSelected = imageSelected,
            onTitleClick = onTitleClick,
            onLinearClick = onLinearClick
        )
    }
}

/**
 * 체크박스 + 텍스트가 가로로 2개 있는 Composable
 */
@Composable
fun CheckBoxes(
    linearSelected: Boolean,
    imageSelected: Boolean,
    onTitleClick: (Boolean) -> Unit,
    onLinearClick: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = imageSelected,
            onCheckedChange = onTitleClick
        )
        Text("Image Title")
        Spacer(modifier = Modifier.width(20.dp))
        Checkbox(
            checked = linearSelected,
            onCheckedChange = onLinearClick
        )
        Text("Linear Progress")
    }
}

/**
 * 이미지에 표시할 Composable
 */
@Composable
fun TitleImage(drawing: Int) {
    Image(
        painter = painterResource(drawing),
        contentDescription = "title image"
    )
}

@Preview(showSystemUi = true)
@Composable
fun Preview() {
    MainScreen()
}