package org.juanpavon.thirtydays

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.juanpavon.thirtydays.data.tips
import org.juanpavon.thirtydays.ui.theme.ThirtyDaysTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThirtyDaysTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        ThirtyDaysTopAppBar()
                    }
                ) { innerPadding ->
                    ThirtyDaysApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ThirtyDaysApp(modifier: Modifier = Modifier) {
    TipsList(tips = tips, modifier = modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThirtyDaysTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = { 
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.displayLarge
            )
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ThirtyDaysTheme {
        ThirtyDaysApp()
    }
}