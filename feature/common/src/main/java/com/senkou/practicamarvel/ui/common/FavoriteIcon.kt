package com.senkou.practicamarvel.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FavoriteIcon(favorite: Boolean, modifier: Modifier = Modifier) {
  Icon(
    modifier = modifier,
    tint = if (favorite) com.senkou.practicamarvel.ui.common.theme.rojoMarvel else LocalContentColor.current,
    imageVector = if (favorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
    contentDescription = "Favorite"
  )
}