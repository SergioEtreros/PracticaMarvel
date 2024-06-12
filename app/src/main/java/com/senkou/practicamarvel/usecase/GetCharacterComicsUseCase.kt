package com.senkou.practicamarvel.usecase

import com.senkou.practicamarvel.data.CharactersRepository

class GetCharacterComicsUseCase(
  private val repository: CharactersRepository
) {
  operator fun invoke(characterId: Int) = repository.getComicsByCharacterId(characterId)
}