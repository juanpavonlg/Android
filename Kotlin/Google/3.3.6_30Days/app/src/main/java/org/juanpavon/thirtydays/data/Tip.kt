package org.juanpavon.thirtydays.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import org.juanpavon.thirtydays.R

data class Tip(
    @StringRes val titleRes: Int,
    @DrawableRes val imageRes: Int,
    @StringRes val contentDescriptionRes: Int,
    @StringRes val descriptionRes: Int
)

val tips = listOf(
    Tip(
        titleRes = R.string.day1_title,
        imageRes = R.drawable.factory_pattern_uml_diagram,
        contentDescriptionRes = R.string.day1_content_description,
        descriptionRes = R.string.day1_description
    ),
    Tip(
        titleRes = R.string.day2_title,
        imageRes = R.drawable.abstractfactory_pattern_uml_diagram,
        contentDescriptionRes = R.string.day2_content_description,
        descriptionRes = R.string.day2_description
    ),
    Tip(
        titleRes = R.string.day3_title,
        imageRes = R.drawable.singleton_pattern_uml_diagram,
        contentDescriptionRes = R.string.day3_content_description,
        descriptionRes = R.string.day3_description
    )
)
