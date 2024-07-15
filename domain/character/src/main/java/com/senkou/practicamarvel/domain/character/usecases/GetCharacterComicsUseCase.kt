package com.senkou.practicamarvel.domain.character.usecases

class GetCharacterComicsUseCase(
  private val repository: com.senkou.practicamarvel.domain.character.data.CharactersRepository
) {
  operator fun invoke(characterId: Int) = repository.getComicsByCharacterId(characterId)
}