package com.senkou.practicamarvel.framework

import com.senkou.practicamarvel.data.datasource.CharactersLocalDataSource
import com.senkou.practicamarvel.domain.model.Comic
import com.senkou.practicamarvel.framework.database.room.model.dao.CharacterDao
import com.senkou.practicamarvel.framework.database.room.model.dao.ComicsDao
import com.senkou.practicamarvel.framework.database.room.model.entities.Character
import com.senkou.practicamarvel.framework.database.room.model.entities.Comics
import com.senkou.practicamarvel.domain.model.Character as DomainCharacter

class CharactersRoomDataSource(
  private val characterDao: CharacterDao,
  private val comicsDao: ComicsDao
) : CharactersLocalDataSource {

  override val characters = characterDao.getCharacters()

  override fun getCharacter(id: Int) = characterDao.getCharacter(id)

  override suspend fun isEmpty() = characterDao.count() <= 0

  override suspend fun saveCharacter(character: DomainCharacter) =
    characterDao.insertCharacter(character.toRoomModel())

  override suspend fun saveAllCharacters(characters: List<DomainCharacter>) =
    characterDao.insertCharacters(characters.map { it.toRoomModel() })

  override fun getComicsByCharacterId(id: Int) = comicsDao.getComicsByCharacterId(id)

  override suspend fun saveComics(comics: List<Comic>) =
    comicsDao.insertComics(comics.map { it.toRoomModel() })

  override suspend fun deleteAll() = characterDao.deleteAll()
}

private fun Comic.toRoomModel(): Comics =
  Comics(
    id = id,
    characterId = characterId,
    imgUrl = imgUrl
  )

private fun DomainCharacter.toRoomModel(): Character =
  Character(
    id = id,
    name = name,
    description = description,
    imageUrl = imageUrl,
    favorite = favorite
  )