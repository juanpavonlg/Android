package org.juanpavon.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.juanpavon.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    val albums = 17
    var album by remember { mutableIntStateOf(0) }
    val imageResource = when(album) {
        0 -> R.drawable.soda_stereo
        1 -> R.drawable.nada_personal
        2 -> R.drawable.signos
        3 -> R.drawable.ruido_blanco
        4 -> R.drawable.doble_vida
        5 -> R.drawable.languis
        6 -> R.drawable.cancion_animal
        7 -> R.drawable.rex_mix
        8 -> R.drawable.dynamo
        9 -> R.drawable.zona_de_promesas
        10 -> R.drawable.sueno_stereo
        11 -> R.drawable.comfort_y_musica_para_volar
        12 -> R.drawable.el_ultimo_concierto_a
        13 -> R.drawable.el_ultimo_concierto_b
        14 -> R.drawable.gira_me_veras_volver_1
        15 -> R.drawable.gira_me_veras_volver_2
        else -> R.drawable.sep7imo_dia
    }
    val descriptionResource = when(album) {
        0 -> R.string.soda_stereo
        1 -> R.string.nada_personal
        2 -> R.string.signos
        3 -> R.string.ruido_blanco
        4 -> R.string.doble_vida
        5 -> R.string.languis
        6 -> R.string.cancion_animal
        7 -> R.string.rex_mix
        8 -> R.string.dynamo
        9 -> R.string.zona_de_promesas
        10 -> R.string.suenno_stereo
        11 -> R.string.comfort_y_musica_para_volar
        12 -> R.string.el_ultimo_concierto_a
        13 -> R.string.el_ultimo_concierto_b
        14 -> R.string.gira_me_veras_volver_1
        15 -> R.string.gira_me_veras_volver_2
        else -> R.string.sep7imo_dia
    }
    val titleResource = when(album) {
        0 -> R.string.soda_stereo
        1 -> R.string.nada_personal
        2 -> R.string.signos
        3 -> R.string.ruido_blanco
        4 -> R.string.doble_vida
        5 -> R.string.languis
        6 -> R.string.cancion_animal
        7 -> R.string.rex_mix
        8 -> R.string.dynamo
        9 -> R.string.zona_de_promesas
        10 -> R.string.suenno_stereo
        11 -> R.string.comfort_y_musica_para_volar
        12 -> R.string.el_ultimo_concierto_a
        13 -> R.string.el_ultimo_concierto_b
        14 -> R.string.gira_me_veras_volver_1
        15 -> R.string.gira_me_veras_volver_2
        else -> R.string.sep7imo_dia
    }
    val year= when(album) {
        0 -> 1984
        1 -> 1985
        2 -> 1986
        3 -> 1987
        4 -> 1988
        5 -> 1989
        6 -> 1990
        7 -> 1991
        8 -> 1992
        9 -> 1993
        10 -> 1995
        11 -> 1996
        12 -> 1997
        13 -> 1997
        14 -> 2008
        15 -> 2008
        else -> 2017
    }

    Column(
        modifier = Modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        ArtworkWall(
            imageResource = imageResource,
            descriptionResource = descriptionResource,
            modifier = Modifier
                .weight(1.0f)
                .wrapContentSize(Alignment.Center)
        )
        Spacer(modifier = Modifier.height(20.dp))
        ArtworkDescriptor(
            titleResource = titleResource,
            year = year,
            modifier = Modifier
                .background(color = Color(0xFFECEBF4))
                .padding(16.dp)
                .width(480.dp)
        )
        Spacer(modifier = Modifier.height(28.dp))
        DisplayController(
            prevClick = { album = (album + albums - 1) % albums },
            nextClick = { album = ++album % albums },
            modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun ArtworkWall(
    @DrawableRes imageResource: Int,
    @StringRes descriptionResource: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            shadowElevation = 8.dp,
            color = Color.Yellow
        ) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = stringResource(
                    id = R.string.description_album_cover,
                    stringResource(id = descriptionResource)
                ),
                modifier = Modifier.padding(36.dp),
            )
        }
    }
}

@Composable
fun ArtworkDescriptor(@StringRes titleResource: Int, year: Int, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = titleResource),
            fontSize = 24.sp,
            fontWeight = FontWeight.Light
        )
        Text(
            text = buildAnnotatedString {
                val text = stringResource(id = R.string.artist, year)
                val start = text.indexOf('(')
                val end = start + 6

                append(text)
                addStyle(SpanStyle(fontWeight = FontWeight.Light), start, end)
            },
            fontWeight = FontWeight.Bold

        )
    }
}

@Composable
fun DisplayController(prevClick: () -> Unit, nextClick: () -> Unit,  modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = prevClick,
            modifier = Modifier.width(152.dp)
        ) {
            Text(text = stringResource(id = R.string.previous))
        }
        Button(
            onClick = nextClick,
            modifier = Modifier.width(152.dp)
        ) {
            Text(text = stringResource(id = R.string.next))
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}
