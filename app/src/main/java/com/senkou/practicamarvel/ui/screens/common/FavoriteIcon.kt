package com.senkou.practicamarvel.ui.screens.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.senkou.practicamarvel.ui.theme.rojoMarvel

@Composable
fun FavoriteIcon(favorite: Boolean, modifier: Modifier = Modifier) {
  Icon(
    modifier = modifier,
    tint = if (favorite) rojoMarvel else LocalContentColor.current,
    imageVector = if (favorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
    contentDescription = "Favorite"
  )
}