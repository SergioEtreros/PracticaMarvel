package com.senkou.practicamarvel.data.network.pokemon.model


import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetail(
  val abilities: List<Ability>,
  val height: Int,
  val id: Int,
  val name: String,
  val order: Int,
  val species: Species,
  val weight: Int
)