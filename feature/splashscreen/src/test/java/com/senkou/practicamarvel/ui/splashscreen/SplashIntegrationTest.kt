package com.senkou.practicamarvel.ui.splashscreen

import app.cash.turbine.test
import com.senkou.practicamarvel.domain.character.entities.Character
import com.senkou.practicamarvel.domain.character.entities.Comic
import com.senkou.practicamarvel.domain.character.usecases.GetCharacterListUseCase
import com.senkou.practicamarvel.test.data.buildCharacterRepositoryWith
import com.senkou.practicamarvel.test.rules.CoroutinesTestRule
import com.senkou.practicamarvel.test.unit.sampleCharacters
import com.senkou.practicamarvel.ui.common.Result
import com.senkou.practicamarvel.ui.home.HomeScreenViewmodel
import junit.framework.TestCase.assertEquals

import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class SplashIntegrationTest {

   @get:Rule
   val coroutinesTestRule = CoroutinesTestRule()

   @Test
   fun name() = runTest {
      val localData = sampleCharacters(1, 3, 5)
      val vm = buildViewmodelWith(localDataCharacter = localData)

      vm.state.test {
         assertEquals(Result.Loading, awaitItem())
         assertEquals(Result.Success(localData), awaitItem())
      }
   }

   private fun buildViewmodelWith(
      localDataCharacter: List<Character> = emptyList(),
      localDataComics: List<Comic> = emptyList(),
      remoteDataCharacter: List<Character> = emptyList(),
   ) = HomeScreenViewmodel(
      getCharacterListUseCase = GetCharacterListUseCase(
         buildCharacterRepositoryWith(localDataCharacter, localDataComics, remoteDataCharacter)
      )
   )
}
