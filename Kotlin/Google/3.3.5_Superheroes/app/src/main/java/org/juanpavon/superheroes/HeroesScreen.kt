package org.juanpavon.superheroes

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.juanpavon.superheroes.model.Hero
import org.juanpavon.superheroes.model.HeroesRepository
import org.juanpavon.superheroes.ui.theme.SuperheroesTheme

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HeroesList(heroes: List<Hero>, modifier: Modifier = Modifier) {
    val visibleState = remember {
        MutableTransitionState(false).apply { targetState = true }
    }

    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(animationSpec = spring(dampingRatio = Spring.DampingRatioHighBouncy)),
        exit = fadeOut(),
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier.padding(
                horizontal = dimensionResource(id = R.dimen.spacing_medium),
                vertical = dimensionResource(id = R.dimen.spacing_small)
            ),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_small))
        ) {
            itemsIndexed(heroes) { index, hero ->
                HeroListItem(
                    hero = hero,
                    modifier = Modifier
                        .animateEnterExit(
                            enter = slideInVertically(
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioLowBouncy,
                                    stiffness = Spring.StiffnessVeryLow
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
fun HeroListItem(hero: Hero, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(id = R.dimen.elevation)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.spacing_medium))
        ) {
            HeroInformation(
                heroName = hero.nameRes,
                heroDescription = hero.descriptionRes,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.spacing_medium)))
            HeroImage(heroImage = hero.imageRes)
        }
    }
}

@Composable
fun HeroInformation(
    @StringRes heroName: Int,
    @StringRes heroDescription: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = heroName),
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            text = stringResource(id = heroDescription),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun HeroImage(
    @DrawableRes heroImage: Int,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = heroImage),
            contentDescription = null,
            modifier = Modifier
                .clip(MaterialTheme.shapes.small)
                .size(dimensionResource(id = R.dimen.image_size)),
        )
    }
}

@Preview("Light Theme")
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HeroPreview() {
    SuperheroesTheme {
        HeroListItem(Hero(R.string.hero1, R.string.description1, R.drawable.android_superhero1))
    }
}

@Preview(showBackground = true)
@Composable
fun HeroesPreview() {
    SuperheroesTheme {
        HeroesList(HeroesRepository.heroes)
    }
}
