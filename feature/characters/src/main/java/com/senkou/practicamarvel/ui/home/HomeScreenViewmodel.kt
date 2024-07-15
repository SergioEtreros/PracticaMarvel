package com.senkou.practicamarvel.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senkou.practicamarvel.ui.common.stateAsResultIn

class HomeScreenViewmodel(
  getCharacterListUseCase: com.senkou.practicamarvel.domain.character.usecases.GetCharacterListUseCase,
) : ViewModel() {

  val state = getCharacterListUseCase().stateAsResultIn(viewModelScope)
}

