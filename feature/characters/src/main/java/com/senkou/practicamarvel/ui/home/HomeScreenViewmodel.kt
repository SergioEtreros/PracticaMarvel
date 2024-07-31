package com.senkou.practicamarvel.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senkou.practicamarvel.ui.common.stateAsResultIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewmodel @Inject constructor(
   getCharacterListUseCase: com.senkou.practicamarvel.domain.character.usecases.GetCharacterListUseCase,
) : ViewModel() {

   val state = getCharacterListUseCase().stateAsResultIn(viewModelScope)
}

