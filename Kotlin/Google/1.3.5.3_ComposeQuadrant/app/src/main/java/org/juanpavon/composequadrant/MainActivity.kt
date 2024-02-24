package org.juanpavon.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.juanpavon.composequadrant.ui.theme.ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeQuadrantTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ComposeQuadrantApp()
                }
            }
        }
    }
}

@Composable
fun ComposeQuadrantApp() {
    Column {
        Row(modifier = Modifier.weight(1.0f)) {
            Quadrant(
                backgroundColor = Color(0xFFEADDFF),
                title = stringResource(id = R.string.text_composable_title),
                description = stringResource(id = R.string.text_composable_description),
                modifier = Modifier.weight(1.0f)
            )
            Quadrant(
                backgroundColor = Color(0xFFD0BCFF),
                title = stringResource(id = R.string.image_composable_title),
                description = stringResource(id = R.string.image_composable_description),
                modifier = Modifier.weight(1.0f)
            )
        }
        Row(modifier = Modifier.weight(1.0f)) {
            Quadrant(
                backgroundColor = Color(0xFFB69DF8),
                title = stringResource(id = R.string.row_composable_title),
                description = stringResource(id = R.string.row_composable_description),
                modifier = Modifier.weight(1.0f)
            )
            Quadrant(
                backgroundColor = Color(0xFFF6EDFF),
                title = stringResource(id = R.string.column_composable_title),
                description = stringResource(id = R.string.column_composable_description),
                modifier = Modifier.weight(1.0f)
            )
        }
    }
}

@Composable
fun Quadrant(
    backgroundColor: Color,
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(color = backgroundColor)
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = description,
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ComposeQuadrantTheme {
        ComposeQuadrantApp()
    }
}