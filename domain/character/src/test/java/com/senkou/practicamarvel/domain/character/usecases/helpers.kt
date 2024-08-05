package com.senkou.practicamarvel.domain.character.usecases

import com.senkou.practicamarvel.domain.character.entities.Character
import com.senkou.practicamarvel.domain.character.entities.Comic

fun sampleCharacter(id: Int) = Character(
   id = id,
   name = "Name $1d",
   description = "Description $id",
   imageUrl = "Image Url $id",
   favorite = false
)

fun sampleCharacters(vararg ids: Int) = ids.map { sampleCharacter(it) }

fun sampleComic(characterId: Int, id: Int) = Comic(
   id = id,
   characterId = characterId,
   imgUrl = "Image Url $id"
)

fun sampleComics(characterId: Int, vararg ids: Int) =
   ids.map { sampleComic(it, characterId).imgUrl }