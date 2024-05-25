package com.example.composebasics

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composebasics.ui.theme.ComposeBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicsTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    // `by` is a delegate value --> Single Responsibility Principle
    // so, `.value` is no longer needed!
    // source: https://medium.com/@emrememil/delegation-in-kotlin-delegated-properties-efedb595b3aa

    // rememberSaveable --> to remember when there's config change, such as dark more, rotation, etc
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    if (shouldShowOnboarding) {
        OnboardingScreen(onContinueClick = { shouldShowOnboarding = false })
    } else {
        GreetingsView()
    }
}

@Composable
fun GreetingsView(names: List<String> = List(1000) {"$it"}, modifier: Modifier = Modifier) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        // this is equivalent to RecyclerView
        LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
            items(items = names) {name ->
                Greeting(name = name)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    // remember is a keyword that we use to prevent it from re-initiating when
    // Greeting view is being re-composed
    var expanded by remember { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        targetValue = if (expanded) 48.dp else 0.dp,
        animationSpec = tween(
            durationMillis = 500
        )
    )

    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
            .padding(
                horizontal = 8.dp,
                vertical = 4.dp
            )
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding)
            ) {
                Text(
                    text = "Hello, "
                )
                Text(
                    text = "$name!"
                )
            }
            OutlinedButton(
                colors = ButtonDefaults.outlinedButtonColors(containerColor = MaterialTheme.colorScheme.background),
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.inverseSurface),
                onClick = { expanded = !expanded }
            ) {
                Text(if (expanded) "Show less" else "Show more")
            }
        }
    }
}

@Composable
fun OnboardingScreen(onContinueClick: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClick
        ) {
            Text("Continue")
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    ComposeBasicsTheme {
        OnboardingScreen(onContinueClick = {})
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingPreview() {
    ComposeBasicsTheme {
        GreetingsView()
    }
}