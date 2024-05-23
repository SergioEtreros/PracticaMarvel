package com.senkou.practicamarvel.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senkou.practicamarvel.domain.model.Character
import com.senkou.practicamarvel.usecase.GetCharacterListUseCase
import kotlinx.coroutines.launch

class HomeScreenViewmodel(
  private val getCharacterListUseCase: GetCharacterListUseCase,
) : ViewModel() {

  var state by mutableStateOf(UiState(isLoading = true))
    private set

  fun loadList(
    loadingEnd: () -> Unit
  ) {
    viewModelScope.launch {
      state = UiState(isLoading = false, characters = getCharacterListUseCase.invoke())
      loadingEnd()
    }
  }
}

data class UiState(
  val isLoading: Boolean = false,
  val characters: List<Character> = emptyList(),
)