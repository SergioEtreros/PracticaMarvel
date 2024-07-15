package com.senkou.practicamarvel.framework.network

import com.senkou.practicamarvel.framework.network.model.RemoteComicResult
import com.senkou.practicamarvel.framework.network.model.RemoteResult
import retrofit2.http.GET
import retrofit2.http.Path

interface CharactersService {

  @GET("v1/public/characters?modifiedSince=2010-01-01T19%3A00%3A00-0500&limit=50&offset=50")
  suspend fun getCharacters(): RemoteResult

  @GET("v1/public/characters/{id}")
  suspend fun getCharacterById(@Path("id") id: Int): RemoteResult

  @GET("https://gateway.marvel.com:443/v1/public/characters/{id}/comics?limit=30")
  suspend fun getComicsByCharacterId(@Path("id") id: Int): RemoteComicResult
}

