package com.example.composebookexample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.composebookexample.ui.theme.ComposeBookExampleTheme

class LazyListActivity : ComponentActivity() {

    private var itemArray: Array<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemArray = resources.getStringArray(R.array.car_array)
        setContent {
            ComposeBookExampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(itemArray = itemArray as Array<out String>)
                }
            }
        }
    }
}

@Composable
fun MainScreen(itemArray: Array<out String>) {
    val context = LocalContext.current
    val onListItemClick = { text: String ->
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
    LazyColumn {
        items(itemArray) { model ->
            MyListItem(item = model, onItemClick = onListItemClick)
        }
    }
}

@Composable
fun ImageLoader(item: String) {
    val url = "https://www.ebookfrenzy.com/book_examples/car_logos/${item.substringBefore(" ")}_logo.png"

    Image(
        painter = rememberImagePainter(data = url),
        contentDescription = "car image",
        contentScale = ContentScale.Fit,
        modifier = Modifier.size(75.dp)
    )
}

@Composable
fun MyListItem(item: String, onItemClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onItemClick(item) },
        shape = RoundedCornerShape(10.dp),
        elevation = 5.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageLoader(item = item)
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = item,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview12() {
    val itemArray: Array<String> = arrayOf("Cadillac Eldorado", "Ford Fairlane", "Plymouth Fury")
    ComposeBookExampleTheme {
        MainScreen(itemArray = itemArray)
    }
}