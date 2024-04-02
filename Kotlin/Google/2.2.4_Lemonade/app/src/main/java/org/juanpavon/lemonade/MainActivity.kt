package org.juanpavon.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.juanpavon.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeApp() {
    var step by remember { mutableIntStateOf(0) }
    var squeezes by remember { mutableIntStateOf(0) }
    val imageResource = when(step) {
        0 -> R.drawable.lemon_tree
        1 -> R.drawable.lemon_squeeze
        2 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    val descriptionResource = when(step) {
        0 -> R.string.lemon_tree_content_description
        1 -> R.string.lemon_content_description
        2 -> R.string.glass_lemonade_content_description
        else -> R.string.empty_glass_content_description
    }
    val textResource = when(step) {
        0 -> R.string.tap_tree
        1 -> R.string.tap_lemon
        2 -> R.string.tap_lemonade
        else -> R.string.tap_glass
    }
    if (step == 1) {
        squeezes = (1..3).random()
    }
    ImageAndText(
        imageResource = imageResource,
        descriptionResource = descriptionResource,
        textResource = textResource,
        onClick = { if (squeezes > 0) squeezes-- else step = ++step % 4 },
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
fun ImageAndText(
    imageResource: Int,
    descriptionResource: Int,
    textResource: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = stringResource(id = descriptionResource),
            modifier = Modifier
                .background(
                    color = Color(color = 0xFFc3ecd2),
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.image_corner_radius))
                )
                .border(
                    width = 2.dp,
                    color = Color(red = 105, green = 205, blue = 216),
                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.image_corner_radius))
                )
                .clickable(onClick = onClick)
                .height(272.dp)
                .padding(24.dp)
                .width(224.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(id = textResource),
            fontSize = 18.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}
