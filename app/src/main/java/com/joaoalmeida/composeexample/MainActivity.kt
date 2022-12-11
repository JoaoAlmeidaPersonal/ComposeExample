package com.joaoalmeida.composeexample

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joaoalmeida.composeexample.compose.TvShowListItem
import com.joaoalmeida.composeexample.data.TvShowList
import com.joaoalmeida.composeexample.data.TvShowList.tvShows
import com.joaoalmeida.composeexample.model.TvShow
import com.joaoalmeida.composeexample.ui.theme.ComposeExampleTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DisplayTvShows() {
                startActivity(InfoActivity.intent(this,it))
            }
        }
    }
}

@Composable
fun DisplayTvShows(selectedItem: (TvShow) -> Unit) {
    val tvShows = remember {
        TvShowList.tvShows
    }
    LazyColumn(contentPadding = PaddingValues(
        horizontal = 16.dp,
        vertical = 8.dp
    )){
        items(
            items = tvShows,
            itemContent = {
            TvShowListItem(tvShow = it, selectedItem)
            }
        )
    }
}

@Composable
fun ScrollableColumnDemo() {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.verticalScroll(scrollState)) {
        for (i in 1..100) {
            Text(
                "Username $i",
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(10.dp)
            )
            Divider(
                color = Red,
                thickness = 5.dp
            )
        }
    }
}

@Composable
fun LazyColumnDemo() {
    LazyColumn() {
        items(100) {
            Text(
                "Username $it",
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(10.dp)
            )
            Divider(
                color = Red,
                thickness = 5.dp
            )
        }
    }
}

@Composable
fun LazyColumnDemo2(selectedItem: (String) -> (Unit)) {
    LazyColumn() {
        items(100) {
            Text(
                "Username $it",
                style = MaterialTheme.typography.h3,
                modifier = Modifier
                    .padding(10.dp)
                    .clickable { selectedItem("$it Selected") }
            )
            Divider(
                color = Red,
                thickness = 5.dp
            )
        }
    }
}

@Composable
fun BoxExample1() {
    Box(
        modifier = Modifier
            .background(color = Green)
            .size(180.dp, 300.dp)
    ) {
        Box(
            modifier = Modifier
                .background(color = Yellow)
                .size(125.dp, 100.dp)
                .align(Alignment.TopEnd)
        )
        Text(
            text = "Hi",
            style = MaterialTheme.typography.h3,
            modifier = Modifier
                .background(color = White)
                .size(90.dp, 50.dp)
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun BoxExample2() {
    Box(
        modifier = Modifier
            .background(color = LightGray)
            .fillMaxSize()
    ) {
        Text(
            text = "Yo",
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .background(Yellow)
                .padding(10.dp)
                .align(Alignment.TopCenter)
        )
    }
}

@Composable
fun BoxExample3() {
    Box {
        Image(
            painter = painterResource(id = R.drawable.beach_resort),
            contentDescription = "Beach Resort"
        )
        Text(
            text = "Beach Resort",
            style = MaterialTheme.typography.h4,
            color = Black,
            modifier = Modifier
                .align(Alignment.BottomStart)
        )
        Button(
            onClick = {},
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = White,
                contentColor = DarkGray
            ),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(10.dp)
                .border(5.dp, DarkGray, RectangleShape)
        ) {
            Text(text = "Add to Cart")
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "$name!",
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        color = Cyan,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .background(color = Yellow)
            .border(2.dp, color = Green)
            .padding(10.dp)

    )
}

@Composable
fun ButtonDemo() {
    val context = LocalContext.current
    Button(onClick = {
        Toast.makeText(context, "Clicked on Button", Toast.LENGTH_SHORT)
            .show()
    }) {
        Text(text = "Add to Cart")
    }
    Button(
        onClick = {
            Toast.makeText(context, "Clicked on Button", Toast.LENGTH_SHORT)
                .show()
        },
        enabled = false
    ) {
        Text(text = "Add to Cart")
    }
    TextButton(onClick = {
        Toast.makeText(context, "Clicked on Text Button", Toast.LENGTH_SHORT)
            .show()
    }) {
        Text(text = "Add to Cart")
    }
    OutlinedButton(onClick = {
        Toast.makeText(context, "Clicked on Outlined Button", Toast.LENGTH_SHORT)
            .show()
    }) {
        Text(text = "Add to Cart")
    }
    IconButton(onClick = {
        Toast.makeText(context, "Clicked on IconButton", Toast.LENGTH_SHORT)
            .show()
    }) {
        Icon(
            Icons.Filled.Refresh,
            contentDescription = "Refresh",
            tint = Gray,
            modifier = Modifier.size(80.dp)
        )
    }
    Button(
        onClick = {
            Toast.makeText(context, "Clicked on Button", Toast.LENGTH_SHORT)
                .show()
        },
        contentPadding = PaddingValues(16.dp),
        border = BorderStroke(10.dp, Black),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = DarkGray,
            contentColor = White
        )
    ) {
        Text(
            text = "Add to Cart",
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(5.dp)
        )
    }

    Button(
        onClick = {
            Toast.makeText(context, "Clicked on Button", Toast.LENGTH_SHORT)
                .show()
        },
        shape = CutCornerShape(10.dp),
        contentPadding = PaddingValues(16.dp),
        border = BorderStroke(10.dp, Black),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = DarkGray,
            contentColor = White
        )
    ) {
        Text(
            text = "Add to Cart",
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(5.dp)
        )
    }
    Button(
        onClick = {
            Toast.makeText(context, "Clicked on Button", Toast.LENGTH_SHORT)
                .show()
        },
        shape = androidx.compose.foundation.shape.CircleShape,
        contentPadding = PaddingValues(16.dp),
        border = BorderStroke(10.dp, Black),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = DarkGray,
            contentColor = White
        )
    ) {
        Text(
            text = "Add to Cart",
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(5.dp)
        )
    }
}

@Composable
fun DisplaySnackBar() {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    Scaffold(scaffoldState = scaffoldState) {
        Button(onClick = {
            coroutineScope.launch {
                val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                    message = "This is message",
                    actionLabel = "Undo",
                    duration = SnackbarDuration.Indefinite
                )
                when (snackBarResult) {
                    SnackbarResult.ActionPerformed -> Log.i("MYTAG", "Action label Clicked")
                    SnackbarResult.Dismissed -> Log.i("MYTAG", "Action label Dismissed")

                }
            }
        }) {
            Text(text = "Display SnackBar")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeExampleTheme {
        Greeting("Android")
    }
}