package com.senkou.practicamarvel.data.local.room

import com.senkou.practicamarvel.data.local.room.model.dao.CharacterDao
import com.senkou.practicamarvel.data.local.room.model.dao.ComicsDao
import com.senkou.practicamarvel.data.local.room.model.entities.Character
import com.senkou.practicamarvel.data.local.room.model.entities.Comics

class CharactersLocalDataSource(
  private val characterDao: CharacterDao,
  private val comicsDao: ComicsDao
) {

  val characters = characterDao.getCharacters()

  fun getCharacter(id: Int) = characterDao.getCharacter(id)

  suspend fun isEmpty() = characterDao.count() <= 0

  suspend fun saveCharacter(character: Character) = characterDao.insertCharacter(character)

  suspend fun saveAllCharacters(characters: List<Character>) =
    characterDao.insertCharacters(characters)

  fun getComicsByCharacterId(id: Int) = comicsDao.getComicsByCharacterId(id)

  suspend fun saveComics(comics: List<Comics>) = comicsDao.insertComics(comics)

  suspend fun deleteAll() = characterDao.deleteAll()
}