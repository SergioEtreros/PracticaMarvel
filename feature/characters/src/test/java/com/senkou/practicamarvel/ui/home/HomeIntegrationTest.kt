package com.senkou.practicamarvel.ui.home

import app.cash.turbine.test
import com.senkou.practicamarvel.domain.character.entities.Character
import com.senkou.practicamarvel.domain.character.entities.Comic
import com.senkou.practicamarvel.domain.character.usecases.GetCharacterListUseCase
import com.senkou.practicamarvel.test.data.buildCharacterRepositoryWith
import com.senkou.practicamarvel.test.rules.CoroutinesTestRule
import com.senkou.practicamarvel.test.unit.sampleCharacters
import com.senkou.practicamarvel.ui.common.Result
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class HomeIntegrationTest {

   @get:Rule
   val coroutinesTestRule = CoroutinesTestRule()

   @Test
   fun `Data is loaded from server when local data source is empty`() = runTest {
      val remoteData = sampleCharacters(1, 3, 5)
      val vm = buildViewmodelWith(remoteDataCharacter = remoteData)

      vm.state.test {
         assertEquals(Result.Loading, awaitItem())
         assertEquals(Result.Success(emptyList<Character>()), awaitItem())
         assertEquals(Result.Success(remoteData), awaitItem())
      }
   }

   @Test
   fun `Data is loaded from local data source when it is not empty`() = runTest {
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