package com.senkou.practicamarvel.framework.database

import com.senkou.practicamarvel.domain.character.entities.Comic
import com.senkou.practicamarvel.framework.database.dao.CharacterDao
import com.senkou.practicamarvel.framework.database.dao.ComicsDao
import com.senkou.practicamarvel.framework.database.entities.Character
import com.senkou.practicamarvel.framework.database.entities.Comics
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.senkou.practicamarvel.domain.character.entities.Character as DomainCharacter

internal class CharactersRoomDataSource @Inject constructor(
  private val characterDao: CharacterDao,
  private val comicsDao: ComicsDao
) : com.senkou.practicamarvel.domain.character.data.CharactersLocalDataSource {

  override val characters =
    characterDao.getCharacters().map { it.map { character -> character.toDomainCharacter() } }

  override fun getCharacter(id: Int) = characterDao.getCharacter(id).map { it?.toDomainCharacter() }

  override suspend fun isEmpty() = characterDao.count() <= 0

  override suspend fun saveCharacter(character: DomainCharacter) =
    characterDao.insertCharacter(character.toRoomModel())

  override suspend fun saveAllCharacters(characters: List<DomainCharacter>) =
    characterDao.insertCharacters(characters.map { it.toRoomModel() })

  override fun getComicsByCharacterId(id: Int) =
    comicsDao.getComicsByCharacterId(id).map { comics -> comics.map { it.imgUrl } }

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

private fun Character.toDomainCharacter(): DomainCharacter =
  DomainCharacter(
    id = id,
    name = name,
    description = description,
    imageUrl = imageUrl,
    favorite = favorite
  )