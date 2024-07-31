package com.senkou.practicamarvel.ui.navigation

import com.senkou.practicamarvel.framework.core.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

   @Provides
   @Singleton
   @Named("api_key")
   fun provideApiKey() = BuildConfig.MARVEL_API_KEY

   @Provides
   @Singleton
   @Named("priv_api_key")
   fun providePrivApiKey() = BuildConfig.MARVEL_PRIV_API_KEY
}