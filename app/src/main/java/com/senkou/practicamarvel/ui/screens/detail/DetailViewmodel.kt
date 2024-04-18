package com.senkou.practicamarvel.ui.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senkou.practicamarvel.data.network.marvel.CharactersRepository
import com.senkou.practicamarvel.data.network.pokemon.PokemonRepository
import com.senkou.practicamarvel.domain.model.Character
import kotlinx.coroutines.launch

class DetailViewmodel(private val characterId: Int) : ViewModel() {


  var state by mutableStateOf(UiState(isLoading = true))
    private set

  private val repository = CharactersRepository()
  private val repository2 = PokemonRepository()

  init {
    viewModelScope.launch {
//      state = UiState(isLoading = false, repository.fetchCharacterById(characterId))
      state = UiState(isLoading = false, repository2.fetchCharacterById(characterId))
    }
  }

  data class UiState(
    val isLoading: Boolean = false,
    val character: Character? = null
  )
}
