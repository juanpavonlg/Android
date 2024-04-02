package org.juanpavon.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.juanpavon.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BusinessCardApp()
                }
            }
        }
    }
}

@Composable
fun BusinessCardApp() {
    Column(modifier = Modifier.background(color = colorResource(id = R.color.green_100))) {
        LogoNameTitle(modifier = Modifier.weight(1.0f))
        ContactInformation()
        Spacer(modifier = Modifier.height(36.dp))
    }
}

@Composable
fun LogoNameTitle(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.android_logo),
            contentDescription = stringResource(id = R.string.logo_description),
            modifier = Modifier
                .background(color = Color(0xFF073042))
                .height(150.dp)
                .width(150.dp)
        )
        Text(
            text = stringResource(id = R.string.full_name),
            modifier = Modifier.padding(bottom = 16.dp),
            fontSize = 28.sp
        )
        Text(
            text = stringResource(id = R.string.title),
            color = colorResource(id = R.color.green_800),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ContactInformation() {
    Column {
        InformationRow(
            imageVector = Icons.Default.Phone,
            contentDescription = stringResource(id = R.string.phone_description),
            information = stringResource(id = R.string.phone))
        InformationRow(
            imageVector = Icons.Default.Share,
            contentDescription = stringResource(id = R.string.share_description),
            information = stringResource(id = R.string.share))
        InformationRow(
            imageVector = Icons.Default.Email,
            contentDescription = stringResource(id = R.string.email_description),
            information = stringResource(id = R.string.email))
    }
}

@Composable
fun InformationRow(
    imageVector: ImageVector,
    contentDescription: String,
    information: String
) {
    Row(modifier = Modifier.padding(bottom = 16.dp)) {
        Column(modifier = Modifier.weight(1.0f)) {
            Icon(
                imageVector = imageVector,
                contentDescription = contentDescription,
                modifier = Modifier
                    .align(alignment = Alignment.End)
                    .padding(end = 12.dp),
                tint = colorResource(id = R.color.green_800)
            )
        }
        Column(modifier = Modifier.weight(2.0f)) {
            Text(
                text = information,
                modifier = Modifier
                    .align(alignment = Alignment.Start)
                    .padding(start = 12.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    BusinessCardTheme {
        BusinessCardApp()
    }
}
