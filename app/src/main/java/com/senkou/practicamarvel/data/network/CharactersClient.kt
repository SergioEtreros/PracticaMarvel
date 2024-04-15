package com.senkou.practicamarvel.data.network

import com.senkou.practicamarvel.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create
import java.math.BigInteger
import java.security.MessageDigest

object CharactersClient {

  private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(::apiKeyAsQuery)
    .build()

  private val json = Json {
    ignoreUnknownKeys = true
  }

  val instance = Retrofit.Builder()
    .baseUrl("https://gateway.marvel.com/")
    .client(okHttpClient)
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .build()
    .create<CharactersService>()

}

private fun apiKeyAsQuery(chain: Interceptor.Chain): Response {

  val ts = System.currentTimeMillis().toString()
  val apikey = BuildConfig.MARVEL_API_KEY
  val hashInput = "$ts${BuildConfig.MARVEL_PRIV_API_KEY}${apikey}"

  return chain.proceed(
    chain.request()
      .newBuilder()
      .url(
        chain.request()
          .url
          .newBuilder()
          .addQueryParameter("ts", ts)
          .addQueryParameter("apikey", apikey)
          .addQueryParameter("hash", hashInput.md5())
          .build()
      )
      .build()
  )
}

fun String.md5(): String {
  val md = MessageDigest.getInstance("MD5")
  return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}