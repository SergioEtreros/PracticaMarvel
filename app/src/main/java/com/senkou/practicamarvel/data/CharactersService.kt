package com.senkou.practicamarvel.data

import retrofit2.http.GET
import retrofit2.http.Path

interface CharactersService {

  @GET("v1/public/characters?limit=50")
  suspend fun getCharacters(): RemoteResult

  @GET("v1/public/characters/{id}")
  suspend fun getCharacterById(@Path("id") id: Int): RemoteResult
}
