package com.senkou.practicamarvel.data.network.pokemon

import com.senkou.practicamarvel.data.model.pokemon.Pokemon
import com.senkou.practicamarvel.data.model.pokemon.PokemonDetail
import com.senkou.practicamarvel.domain.model.Character

class PokemonRepository {
  suspend fun fetchPokemons(): List<Character> =
    PokemonClient.instance
      .getPokemons()
      .pokemonList
      .map {
        it.toDomainModel()
      }

  suspend fun fetchCharacterById(id: Int): Character =
    PokemonClient.instance
      .getPokemonById(id)
      .toDomainModel()
}

private fun Pokemon.toDomainModel(): Character {
  val id =  url.substring(0, url.length - 1).split("/").last().toInt()
  return Character(
    id = id,
    name = name,
    description = "",
    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png",
    comics = emptyList()
  )
}
private fun PokemonDetail.toDomainModel(): Character =
  Character(
    id = id,
    name = name,
    description = "",
    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png",
    comics = abilities.map { it.ability.name }
  )