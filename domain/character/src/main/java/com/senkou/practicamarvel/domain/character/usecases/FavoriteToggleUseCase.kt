package com.senkou.practicamarvel.domain.character.usecases

class FavoriteToggleUseCase(
  private val repository: com.senkou.practicamarvel.domain.character.data.CharactersRepository
) {
  suspend operator fun invoke(character: com.senkou.practicamarvel.domain.character.entities.Character) =
    repository.favoriteToggle(character)
}