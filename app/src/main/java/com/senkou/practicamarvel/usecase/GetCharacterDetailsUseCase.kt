package com.senkou.practicamarvel.usecase

import com.senkou.practicamarvel.data.network.marvel.CharactersRepository

class GetCharacterDetailsUseCase(
  private val repository: CharactersRepository
) {
  suspend operator fun invoke(charaterId: Int) = repository.fetchCharacterById(charaterId)
}