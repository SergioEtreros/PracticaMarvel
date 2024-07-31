package com.senkou.practicamarvel.domain.character.usecases

import com.senkou.practicamarvel.domain.character.data.CharactersRepository
import javax.inject.Inject

class GetCharacterDetailsUseCase @Inject constructor(
  private val repository: CharactersRepository
) {
  operator fun invoke(charaterId: Int) = repository.fetchCharacterById(charaterId)
}