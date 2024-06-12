package com.senkou.practicamarvel.usecase

import com.senkou.practicamarvel.data.CharactersRepository

class GetCharacterListUseCase(
  private val repository: CharactersRepository
) {
  operator fun invoke() = repository.characters
}