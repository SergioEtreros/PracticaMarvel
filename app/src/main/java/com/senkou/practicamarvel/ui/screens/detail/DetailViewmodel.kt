package com.senkou.practicamarvel.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senkou.practicamarvel.domain.model.Character
import com.senkou.practicamarvel.usecase.GetCharacterComicsUseCase
import com.senkou.practicamarvel.usecase.GetCharacterDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewmodel(
  private val characterId: Int,
  private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase,
  private val getCharacterComicsUseCase: GetCharacterComicsUseCase
) : ViewModel() {

//  var state by mutableStateOf(UiState(isLoading = true))
//    private set

  private var _state = MutableStateFlow(UiState(isLoading = true))
  val state get() = _state.asStateFlow()

  init {
    viewModelScope.launch {
      _state.value = UiState(
        isLoading = false,
        character = getCharacterDetailsUseCase.invoke(characterId),
        comics = getCharacterComicsUseCase.invoke(characterId)
      )
    }
  }

  data class UiState(
    val isLoading: Boolean = false,
    val character: Character? = null,
    val comics: List<String> = emptyList(),
    val favorite: Boolean = false,
    val message: String? = null
  )

  fun onFavoriteClick() {
    _state.update {
      it.copy(
        favorite = !it.favorite,
        message = if (!it.favorite) "Added to favorites" else "Removed from favorites"
      )
    }
  }

  fun onMessageShown() {
    _state.update { it.copy(message = null) }
  }
}
