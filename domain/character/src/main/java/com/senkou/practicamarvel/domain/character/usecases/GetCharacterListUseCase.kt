package com.senkou.practicamarvel.domain.character.usecases

class GetCharacterListUseCase(
  private val repository: com.senkou.practicamarvel.domain.character.data.CharactersRepository
) {
  operator fun invoke() = repository.characters
}