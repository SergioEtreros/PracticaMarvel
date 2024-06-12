package com.senkou.practicamarvel.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senkou.practicamarvel.stateAsResultIn
import com.senkou.practicamarvel.usecase.GetCharacterListUseCase

class HomeScreenViewmodel(
  getCharacterListUseCase: GetCharacterListUseCase,
) : ViewModel() {

  val state = getCharacterListUseCase().stateAsResultIn(viewModelScope)
}

