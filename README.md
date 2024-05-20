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


