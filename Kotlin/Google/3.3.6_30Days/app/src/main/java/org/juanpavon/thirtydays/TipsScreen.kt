package org.juanpavon.thirtydays

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import org.juanpavon.thirtydays.data.Tip
import org.juanpavon.thirtydays.data.tips
import org.juanpavon.thirtydays.ui.theme.ThirtyDaysTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TipsList(tips: List<Tip>, modifier: Modifier = Modifier) {
    val visibleState = remember {
        MutableTransitionState(false).apply { targetState = true }
    }

    AnimatedVisibility(
        visibleState = visibleState,
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.spacing_small)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_small))
        ) {
            itemsIndexed(tips) {index, tip ->
                TipItem(
                    tip = tip,
                    index = index + 1,
                    modifier = Modifier
                        .animateEnterExit(
                            enter = slideInVertically(
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioHighBouncy,
                                    stiffness = Spring.StiffnessLow
                                ),
                                initialOffsetY = { it * (index + 1) }
                            )
                        )
                )
            }
        }
    }
}

@Composable
fun TipItem(tip: Tip, index: Int, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colorScheme.tertiaryContainer
        else MaterialTheme.colorScheme.primaryContainer
    )

    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(id = R.dimen.elevation)
        )
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
                .background(color = color)
                .padding(
                    start = dimensionResource(id = R.dimen.spacing_small),
                    top = dimensionResource(id = R.dimen.spacing_tiny),
                    end = dimensionResource(id = R.dimen.spacing_small),
                    bottom = dimensionResource(id = R.dimen.spacing_small)
                )
        ) {
            Column {
                Text(
                    text = stringResource(id = R.string.day, index),
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = stringResource(id = tip.titleRes),
                    style = MaterialTheme.typography.titleLarge
                )
                Image(
                    painter = painterResource(id = tip.imageRes),
                    contentDescription = stringResource(id = tip.contentDescriptionRes),
                    modifier = Modifier
                        .clickable { expanded = !expanded }
                        .clip(shape = MaterialTheme.shapes.small)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }
            if (expanded) {
                Text(
                    text = stringResource(id = tip.descriptionRes),
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Preview("Light Theme")
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun TipPreview() {
    ThirtyDaysTheme {
        TipItem(
            tip = Tip(
                R.string.day1_title,
                R.drawable.factory_pattern_uml_diagram,
                R.string.day1_content_description,
                R.string.day1_description
            ),
            1
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun TipsPreview() {
    ThirtyDaysTheme {
        TipsList(tips = tips)
    }
}
