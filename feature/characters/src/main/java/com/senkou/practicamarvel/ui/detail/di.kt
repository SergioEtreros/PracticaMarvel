package com.senkou.practicamarvel.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.toRoute
import com.senkou.practicamarvel.ui.common.Detail
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
class DetailViewmodelComponent {

   @Provides
   @ViewModelScoped
   @Named("characterId")
   fun provideCharacterId(savedStateHandle: SavedStateHandle): Int =
      savedStateHandle.toRoute<Detail>().characterId
}