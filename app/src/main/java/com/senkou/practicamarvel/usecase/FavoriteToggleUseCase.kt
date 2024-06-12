package com.senkou.practicamarvel.usecase

import com.senkou.practicamarvel.data.CharactersRepository
import com.senkou.practicamarvel.domain.model.Character

class FavoriteToggleUseCase(
  private val repository: CharactersRepository
) {
  suspend operator fun invoke(character: Character) = repository.favoriteToggle(character)
}