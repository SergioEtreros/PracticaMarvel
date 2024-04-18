package com.senkou.practicamarvel.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senkou.practicamarvel.data.network.marvel.CharactersRepository
import com.senkou.practicamarvel.data.network.pokemon.PokemonRepository
import com.senkou.practicamarvel.domain.model.Character
import kotlinx.coroutines.launch

class HomeScreenViewmodel : ViewModel() {

  var state by mutableStateOf(UiState(isLoading = true))
    private set

  private val repository = CharactersRepository()
  private val repository2 = PokemonRepository()

  //  fun getCharacters() {
  init {
    viewModelScope.launch {
//      state = UiState(isLoading = false, characters = repository.fetchCharacters())
      state = UiState(isLoading = false, characters = repository2.fetchPokemons())
    }
  }
}

data class UiState(
  val isLoading: Boolean = false,
  val characters: List<Character> = emptyList(),
)