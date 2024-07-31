package com.senkou.practicamarvel.framework

import com.senkou.practicamarvel.domain.character.data.CharactersLocalDataSource
import com.senkou.practicamarvel.domain.character.data.CharactersRemoteDataSource
import com.senkou.practicamarvel.framework.database.CharactersRoomDataSource
import com.senkou.practicamarvel.framework.network.CharacterServerDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class FrameworkCharacterModule {

   @Binds
   abstract fun bindLocalDataSource(localDataSource: CharactersRoomDataSource): CharactersLocalDataSource

   @Binds
   abstract fun bindRemotaDataSource(remoteDataSource: CharacterServerDataSource): CharactersRemoteDataSource
}

