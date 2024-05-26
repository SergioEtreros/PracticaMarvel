package com.senkou.practicamarvel.usecase

import com.senkou.practicamarvel.data.network.marvel.CharactersRepository

class GetCharacterComicsUseCase(
  private val repository: CharactersRepository
) {
  suspend operator fun invoke(characterId: Int) = repository.getCharacterComics(characterId)
}