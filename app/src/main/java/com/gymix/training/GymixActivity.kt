package com.gymix.training

import android.content.res.Resources
import android.graphics.BitmapShader
import android.graphics.Shader
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gymix.training.ui.theme.GymixTheme

// or just
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.Dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.android.parcel.Parcelize

class GymixActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GymixTheme {
                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    ToDoList(listOf("Review", "UnBlock", "Compose", "aSDASD"))
//                }
                HelloScreen()
            }
        }

//        val expensiveCalculation = remembers {
//            Log.d("AAAA", "Start Calculation")
//            return@remembers 42
//        }
//
//        Log.d("AAAA", expensiveCalculation().toString())
    }
}

data class Message(val author: String, val body: String)

@Composable
fun MessageCard(msg: Message) {
    Row(Modifier.padding(8.dp)) {
        Image(
            painter = painterResource(R.drawable.profile),
            contentDescription = "image content Description",
            modifier = Modifier
                .size(size = 40.dp)
                .clip(shape = CircleShape)
        )


        Spacer(Modifier.width(8.dp))

        // We keep track if the message is expanded or not in this
        // variable
        var isExpanded by remember { mutableStateOf(false) }

        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(msg.author)

            Spacer(Modifier.padding(8.dp))

            Text(
                text = msg.body,
                maxLines = if (isExpanded) 5 else 1
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun previewMessageCard() {
    conversations(SampleData.conversationSample)
}

@Composable
fun conversations(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(message)
        }
    }
}

fun <T> remembers(calculation: () -> T): () -> T {
    var memoized: T? = null
    return {
        if (memoized == null) {
            memoized = calculation()
        }
        memoized!!
    }
}

@Composable
fun MovieScreenWithKey(movies: List<Movie>) {
    LazyColumn {
        items(movies, { movie -> movie.id }) {
            MoviewOverView(it)
        }
    }
}

@Composable
fun MoviewOverView(movie: Movie) {

}

@Composable
fun HomeScreen(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onStart: () -> Unit,
    onStop: () -> Unit
) {
    val currentOnStart by rememberUpdatedState(onStart)
    val currentOnStop by rememberUpdatedState(onStop)

    DisposableEffect(lifecycleOwner) {

        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                currentOnStart()
            } else if (event == Lifecycle.Event.ON_STOP) {
                currentOnStop()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    /* Home Screen Content */
}

@Composable
fun loadNetworkImage(url: String?, repository: ImageRepository): State<Result<Movie>> {
    return produceState(Result.success(Movie(21, "start")), url) {
        val image = repository.load(url)

        value = if (image == null) {
            Result.failure(Throwable())
        } else {
            Result.success(Movie(1, "title"))
        }

    }
}

class ImageRepository {
    fun load(url: String?): Result<Movie>? {
        return Result.success(Movie(2, "afasf"))
    }
}

data class Movie(val id: Int, val title: String)

@Composable
fun ToDoList(highPriorityKeyWords: List<String> = listOf("ccc", "ddd", "eee")) {
    val todoTask = remember { mutableStateListOf("aaa", "bbb", "ccc") }

    val highPriorityTask by remember(highPriorityKeyWords) {
        val item: State<List<String>> = derivedStateOf {
            todoTask.filter { task ->
                highPriorityKeyWords.any { keyword ->
                    task.contains(keyword)
                }
            }
        }
        item
    }

    LazyColumn {
        items(highPriorityTask) { ToDoItem(it) }
        items(todoTask) { ToDoItem(it) }
    }
}

@Composable
fun ToDoItem(item: String) {
    Row(Modifier.padding(8.dp)) {
        Image(
            painter = painterResource(R.drawable.profile),
            contentDescription = "image content Description",
            modifier = Modifier
                .size(size = 40.dp)
                .clip(shape = CircleShape)
        )

        Spacer(Modifier.width(8.dp))

        // We keep track if the message is expanded or not in this
        // variable
        Column {
            Text(item)
        }
    }
}

@Composable
fun create() {
    // State read without property delegate.
    var paddingState: MutableState<Dp> = remember { mutableStateOf(8.dp) }

    // State read with property delegate.
    var padding by remember { mutableStateOf(8.dp) }

    Text(
        "Hello World",
        Modifier.padding(padding)
    )
}


@Composable
fun createBox() {
    Box {
        Log.d("createBox", "remember: 0")
        var imageHeightPx by remember { mutableStateOf(0) }

        Image(painter = painterResource(R.drawable.profile),
            contentDescription = "above",
            modifier = Modifier.fillMaxWidth()
                .onSizeChanged { size ->
                    Log.d("createBox", "onSizeChanged: $size")
//                    imageHeightPx = size.height
                })

        Text(
            text = "I'm below the image",
            modifier = Modifier.padding(top = with(LocalDensity.current) { imageHeightPx.toDp() })
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelloContent() {
    Column(modifier = Modifier.padding(16.dp)) {
        var name by rememberSaveable { mutableStateOf("") }
        if (name.isNotEmpty()) {
            Text("Hello $name", modifier = Modifier.padding(8.dp))
        }

        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Text") })
    }
}

@Composable
fun HelloScreen() {
    var name by remember { mutableStateOf("") }

    HelloContent(name) { name = it }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HelloContent(name: String, onNameChange: (String) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Hello $name", modifier = Modifier.padding(8.dp))
        OutlinedTextField(value = name, onValueChange = onNameChange, label = {})
    }
}

@Composable
fun BackgroundBanner(
    @DrawableRes id: Int,
    modifier: Modifier = Modifier,
    res: Resources = LocalContext.current.resources
) {
    val brush =
        remember {
            ShaderBrush(
                BitmapShader(
                    ImageBitmap.imageResource(res, id).asAndroidBitmap(),
                    Shader.TileMode.REPEAT,
                    Shader.TileMode.REPEAT
                )
            )
        }

    Box(modifier = modifier.background(brush)) {

    }
}


