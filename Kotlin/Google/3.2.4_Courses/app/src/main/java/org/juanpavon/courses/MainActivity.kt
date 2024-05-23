package org.juanpavon.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.juanpavon.courses.data.DataSource
import org.juanpavon.courses.model.Topic
import org.juanpavon.courses.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoursesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CoursesApp()
                }
            }
        }
    }
}

@Composable
fun CoursesApp() {
    TopicGrid(
        topicList = DataSource.topics,
        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
    )
}

@Composable
fun TopicGridItem(topic: Topic, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Row {
            Image(
                painter = painterResource(id = topic.imageResourceId), 
                contentDescription = stringResource(id = topic.stringResourceId),
                modifier = Modifier
                    .height(68.dp)
                    .width(68.dp)
                    .aspectRatio(1.0f),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(
                    top = dimensionResource(id = R.dimen.padding_medium),
                    start = dimensionResource(id = R.dimen.padding_medium),
                    end = dimensionResource(id = R.dimen.padding_medium)
                )
            ) {
                Text(
                    text = stringResource(id = topic.stringResourceId),
                    style = MaterialTheme.typography.bodyMedium,
                )
                Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.padding_small)))
                    Text(
                        text = "${topic.courses}",
                        style = MaterialTheme.typography.labelMedium)
                }
            }
        }
    }
}

@Composable
fun TopicGrid(topicList: List<Topic>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
    ) {
        items(topicList) {topic ->
            TopicGridItem(topic = topic)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoursesTheme {
        CoursesApp()
//        TopicGridItem(topic = Topic(R.drawable.photography, R.string.photography, 321))
    }
}
