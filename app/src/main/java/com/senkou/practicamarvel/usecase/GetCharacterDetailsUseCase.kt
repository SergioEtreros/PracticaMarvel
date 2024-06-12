package com.senkou.practicamarvel.usecase

import com.senkou.practicamarvel.data.CharactersRepository

class GetCharacterDetailsUseCase(
  private val repository: CharactersRepository
) {
  operator fun invoke(charaterId: Int) = repository.fetchCharacterById(charaterId)
}