package com.senkou.practicamarvel.domain.character.usecases

import com.senkou.practicamarvel.domain.character.data.CharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCharacterComicsUseCase @Inject constructor(
   private val repository: CharactersRepository
) {
   suspend operator fun invoke(characterId: Int) =
      withContext(Dispatchers.IO) { repository.getComicsByCharacterId(characterId) }
}