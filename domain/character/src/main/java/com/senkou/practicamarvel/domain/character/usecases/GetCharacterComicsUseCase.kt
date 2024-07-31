package com.senkou.practicamarvel.domain.character.usecases

import com.senkou.practicamarvel.domain.character.data.CharactersRepository
import javax.inject.Inject

class GetCharacterComicsUseCase @Inject constructor(
  private val repository: CharactersRepository
) {
  operator fun invoke(characterId: Int) = repository.getComicsByCharacterId(characterId)
}