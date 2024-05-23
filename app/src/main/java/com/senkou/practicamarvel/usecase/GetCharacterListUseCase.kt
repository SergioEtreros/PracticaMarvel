package com.senkou.practicamarvel.usecase

import com.senkou.practicamarvel.data.network.marvel.CharactersRepository

class GetCharacterListUseCase(
  private val repository: CharactersRepository
) {
  suspend operator fun invoke() = repository.fetchCharacters()
}