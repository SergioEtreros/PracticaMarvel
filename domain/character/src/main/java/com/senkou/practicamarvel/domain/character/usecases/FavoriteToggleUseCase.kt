package com.senkou.practicamarvel.domain.character.usecases

import com.senkou.practicamarvel.domain.character.data.CharactersRepository
import com.senkou.practicamarvel.domain.character.entities.Character
import javax.inject.Inject

class FavoriteToggleUseCase @Inject constructor(
  private val repository: CharactersRepository
) {
  suspend operator fun invoke(character: Character) =
    repository.favoriteToggle(character)
}