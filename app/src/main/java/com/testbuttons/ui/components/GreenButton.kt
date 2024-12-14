package com.testbuttons.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.testbuttons.ui.theme.TestButtonsTheme

val mainGreen = Color(0xFF6abd6a)
val topButtonColor = Color(0xFF5cb860)

@Composable
fun GreenButton() {
    var isPressed by remember { mutableStateOf(false) }
    val buttonDepth = 10.dp
    val buttonShadowSize = 3.dp
    val offset: Dp by animateDpAsState(
        targetValue = if (isPressed) buttonDepth else 0.dp,
        label = "Button animation"
    )

    Box(
        modifier = Modifier
    ) {
        Box(
            modifier = Modifier
                .offset(y = buttonDepth + buttonShadowSize)
                .matchParentSize()
                .alpha(if (offset == 0.dp) 0.08f else 0f)
                .background(
                    color = Color.Black,
                    shape = MaterialTheme.shapes.medium
                )
        )
        Box(
            modifier = Modifier
                .offset(y = buttonDepth)
                .matchParentSize()
                .background(
                    color = mainGreen,
                    shape = MaterialTheme.shapes.medium
                )
        )
        Box(
            modifier = Modifier
                .offset(y = offset)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
                            isPressed = true
                            try {
                                awaitRelease()
                            } finally {
                                isPressed = false
                            }
                        }
                    )
                }
                .background(
                    color = mainGreen,
                    shape = MaterialTheme.shapes.medium
                )
        ) {
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF8ad18a),
                                Color(0xFFf0faf0)
                            )
                        ),
                        shape = MaterialTheme.shapes.medium
                    )
                    .border(
                        width = 1.dp,
                        color = Color(0xFF5fc264),
                        shape = MaterialTheme.shapes.medium
                    )
            ) {
                Box(
                    modifier = Modifier
                        .padding(vertical = 2.dp)
                        .size(140.dp)
                        .then(
                            if (offset == 0.dp) {
                                Modifier
                                    .background(
                                        brush = Brush.verticalGradient(
                                            colors = listOf(
                                                topButtonColor,
                                                Color(0xFF80f286)
                                            )
                                        ),
                                        shape = MaterialTheme.shapes.medium
                                    )
                            } else {
                                Modifier
                                    .background(
                                        color = topButtonColor,
                                        shape = MaterialTheme.shapes.medium
                                    )
                            }
                        )

                ) {
                    Text(
                        text = "Старт",
                        fontSize = 18.sp,
                        color = Color.White,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun GreenButton_Preview() {
    TestButtonsTheme {
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.White),
//            contentAlignment = Alignment.Center
        ) {
            GreenButton()
        }
    }
}