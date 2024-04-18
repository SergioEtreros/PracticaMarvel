package com.senkou.practicamarvel.data.network.pokemon

import com.senkou.practicamarvel.data.model.pokemon.PokemonDetail
import com.senkou.practicamarvel.data.model.pokemon.RemotePokemon
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("pokemon/?limit=40")
    suspend fun getPokemons(): RemotePokemon

    @GET("pokemon/{id}")
    suspend fun getPokemonById(@Path("id") id: Int): PokemonDetail
}