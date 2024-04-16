package com.senkou.practicamarvel.ui.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.senkou.practicamarvel.R
import com.senkou.practicamarvel.ui.screens.Screen
import com.senkou.practicamarvel.ui.theme.AsymetricLarge

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(vm: DetailViewmodel, onBack: () -> Unit) {

  val state = vm.state

  Screen {

    Scaffold(topBar = {
      TopAppBar(title = {
        Text(
          text = state.character?.name ?: "",
          style = MaterialTheme.typography.headlineMedium,
          fontWeight = FontWeight.Bold,
        )
      }, navigationIcon = {
        IconButton(onClick = onBack) {
          Icon(
            imageVector = Icons.AutoMirrored.Default.ArrowBack,
            contentDescription = stringResource(id = R.string.back)
          )
        }
      })
    }

    ) { paddingValues ->
      state.character?.let { character ->

        Column(
          modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 16.dp),
          horizontalAlignment = Alignment.CenterHorizontally
        ) {


          Spacer(modifier = Modifier.height(16.dp))

          AsyncImage(
            model = character.imageUrl,
            contentDescription = character.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
              .fillMaxWidth()
              .aspectRatio(16f / 9f)
              .clip(AsymetricLarge)
              .shadow(8.dp)
          )

          Spacer(modifier = Modifier.height(24.dp))

          if (character.description.isNotEmpty()) {
            Text(
              text = character.description,
              style = MaterialTheme.typography.bodyLarge,
              textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier.height(24.dp))
          }

          Text(
            text = stringResource(R.string.comics),
            style = MaterialTheme.typography.headlineMedium
          )

          Spacer(modifier = Modifier.height(16.dp))

          LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
          ) {
            items(character.comics) { comic ->
              ComicItem(comic = comic)
            }
          }
        }
      }
    }
  }
}