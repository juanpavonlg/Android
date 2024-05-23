package org.juanpavon.courses.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @DrawableRes val imageResourceId: Int,
    @StringRes val stringResourceId: Int,
    val courses: Int
)
