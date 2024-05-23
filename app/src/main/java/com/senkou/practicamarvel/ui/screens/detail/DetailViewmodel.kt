package com.senkou.practicamarvel.ui.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senkou.practicamarvel.domain.model.Character
import com.senkou.practicamarvel.usecase.GetCharacterDetailsUseCase
import kotlinx.coroutines.launch

class DetailViewmodel(
  private val characterId: Int,
  private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase
) : ViewModel() {

  var state by mutableStateOf(UiState(isLoading = true))
    private set

  init {
    viewModelScope.launch {
      state = UiState(isLoading = false, getCharacterDetailsUseCase.invoke(characterId))
    }
  }

  data class UiState(
    val isLoading: Boolean = false,
    val character: Character? = null
  )
}
