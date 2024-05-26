package com.senkou.practicamarvel.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senkou.practicamarvel.domain.model.Character
import com.senkou.practicamarvel.usecase.GetCharacterListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewmodel(
  private val getCharacterListUseCase: GetCharacterListUseCase,
) : ViewModel() {

//  var state by mutableStateOf(UiState(isLoading = true))
//    private set

  private val _state = MutableStateFlow(UiState(isLoading = true))
  val state get() = _state.asStateFlow()

  fun loadList(
    loadingEnd: () -> Unit
  ) {
    viewModelScope.launch {
      _state.value = UiState(isLoading = false, characters = getCharacterListUseCase.invoke())
      loadingEnd()
    }
  }

  data class UiState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
  )
}

