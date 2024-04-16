package com.senkou.practicamarvel.data.network

import com.senkou.practicamarvel.data.model.RemoteResult
import retrofit2.http.GET
import retrofit2.http.Path

interface CharactersService {

//  @GET("v1/public/characters?limit=50&offset=50")
  @GET("v1/public/characters?modifiedSince=2010-01-01T19%3A00%3A00-0500&limit=50&offset=50")
  suspend fun getCharacters(): RemoteResult

  @GET("v1/public/characters/{id}")
  suspend fun getCharacterById(@Path("id") id: Int): RemoteResult
}
