package com.senkou.practicamarvel.data.network

import com.senkou.practicamarvel.data.model.RemoteResult
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("pokemon/?offset=20&limit=20")
    suspend fun getCharacters(): RemoteResult

    @GET("pokemon/{name}")
    suspend fun getCharacterById(@Path("name") name: String): RemoteResult
}
