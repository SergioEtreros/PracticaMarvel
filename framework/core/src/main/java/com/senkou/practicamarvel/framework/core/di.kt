package com.senkou.practicamarvel.framework.core

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object FrameworkCoreModule {

   @Provides
   @Singleton
   fun provideDatabase(app: Application) =
      Room.databaseBuilder(app, MarvelDB::class.java, "marvel").build()

   @Provides
   fun provideCharacterDao(db: MarvelDB) = db.characterDao()

   @Provides
   fun provideComicsDao(db: MarvelDB) = db.comicsDao()

   @Provides
   @Singleton
   fun provideCharactersService(
      @Named("api_key") apiKey: String,
      @Named("priv_api_key") privApiKey: String
   ) = CharactersClient(apiKey, privApiKey).instance
}
