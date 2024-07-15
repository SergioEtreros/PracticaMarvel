package com.senkou.practicamarvel.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senkou.practicamarvel.domain.character.entities.Character
import com.senkou.practicamarvel.domain.character.usecases.FavoriteToggleUseCase
import com.senkou.practicamarvel.domain.character.usecases.GetCharacterComicsUseCase
import com.senkou.practicamarvel.domain.character.usecases.GetCharacterDetailsUseCase
import com.senkou.practicamarvel.ui.common.ifSuccess
import com.senkou.practicamarvel.ui.common.stateAsResultIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewmodel(
  characterId: Int,
  getCharacterDetailsUseCase: GetCharacterDetailsUseCase,
  getCharacterComicsUseCase: GetCharacterComicsUseCase,
  private val favoriteToggleUseCase: FavoriteToggleUseCase
) : ViewModel() {

  private var _state = MutableStateFlow(UiState())
  val state = _state.combine(getCharacterDetailsUseCase(characterId)) { state, character ->
    state.copy(character = character)
  }.combine(getCharacterComicsUseCase(characterId)) { state, comics ->
    state.copy(comics = comics)
  }.stateAsResultIn(viewModelScope)

  data class UiState(
    val character: Character? = null,
    val comics: List<String> = emptyList(),
    val message: String? = null
  )

  fun onFavoriteClick() {
    state.value.ifSuccess {
      viewModelScope.launch {
        it.character?.let { character ->
          _state.update { uiState ->
            uiState.copy(
              message = if (!character.favorite) "Added to favorites" else "Removed from favorites"
            )
          }
          favoriteToggleUseCase(character)
        }
      }
    }
  }

  fun onMessageShown() {
    _state.update { it.copy(message = null) }
  }
}
