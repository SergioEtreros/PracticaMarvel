package com.senkou.practicamarvel.domain.character.usecases

class GetCharacterDetailsUseCase(
  private val repository: com.senkou.practicamarvel.domain.character.data.CharactersRepository
) {
  operator fun invoke(charaterId: Int) = repository.fetchCharacterById(charaterId)
}