package com.senkou.practicamarvel.framework.core

import com.senkou.practicamarvel.framework.network.CharactersService
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create
import java.math.BigInteger
import java.security.MessageDigest

internal class CharactersClient(
  private val apiKey: String,
  private val privApiKey: String
) {

  private val intercepter = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.HEADERS
  }

  private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(::apiKeyAsQuery)
    .addInterceptor(intercepter)
    .build()

  private val json = Json {
    ignoreUnknownKeys = true
  }

  val instance = Retrofit.Builder()
    .baseUrl("https://gateway.marvel.com:443/")
    .client(okHttpClient)
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .build()
    .create<CharactersService>()


  private fun apiKeyAsQuery(chain: Interceptor.Chain): Response {

    val ts = System.currentTimeMillis().toString()
    val hashInput = "$ts${privApiKey}${apiKey}"

    return chain.proceed(
      chain.request()
        .newBuilder()
        .url(
          chain.request()
            .url
            .newBuilder()
            .addQueryParameter("ts", ts)
            .addQueryParameter("apikey", apiKey)
            .addQueryParameter("hash", hashInput.md5())
            .build()
        )
        .build()
    )
  }

  private fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
  }
}

