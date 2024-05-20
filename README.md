# Jetpack Compose

## Composable Functions
### Surface
`Surface` is equivalent to a container in Compose world.
`Surface` itself is Composable:
```
@Composable
@NonRestartableComposable
fun Surface(
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    color: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = contentColorFor(color),
    tonalElevation: Dp = 0.dp,
    shadowElevation: Dp = 0.dp,
    border: BorderStroke? = null,
    content: @Composable () -> Unit
) { ... }
```

## Modifiers
One of the main keys of Jetpack Compose. It modifies your composables.

## Reusing Composables
The more components you add to the UI, the more levels of nesting you create. 
This can affect readability if a function becomes really large. 
By making small reusable components it's easy to build up a library of UI elements used in your app. 
Each one is responsible for one small part of the screen and can be edited independently.

As a best practice, your function should include a `Modifier parameter` that is assigned an `empty Modifier` by default. 
Forward this modifier to the first composable you call inside your function. 
This way, the calling site can adapt layout instructions and behaviors from outside of your composable function.
```
@Composable
fun GreetingsView(modifier: Modifier = Modifier) { // this parameter is best practice!
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        Greeting("Android")
    }
}
```

## Column, Row, and Box
These three composable functions are basic building blocks of Compose.



