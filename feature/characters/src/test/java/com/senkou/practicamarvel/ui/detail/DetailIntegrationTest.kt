package com.senkou.practicamarvel.ui.detail

import app.cash.turbine.test
import com.senkou.practicamarvel.domain.character.usecases.FavoriteToggleUseCase
import com.senkou.practicamarvel.domain.character.usecases.GetCharacterComicsUseCase
import com.senkou.practicamarvel.domain.character.usecases.GetCharacterDetailsUseCase
import com.senkou.practicamarvel.test.data.buildCharacterRepositoryWith
import com.senkou.practicamarvel.test.rules.CoroutinesTestRule
import com.senkou.practicamarvel.test.unit.sampleCharacter
import com.senkou.practicamarvel.test.unit.sampleCharacters
import com.senkou.practicamarvel.test.unit.sampleComicsObject
import com.senkou.practicamarvel.ui.common.Result
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailIntegrationTest {

   @get:Rule
   val coroutinesTestRule = CoroutinesTestRule()

   private lateinit var vm: DetailViewmodel
   private val character = sampleCharacter(3)
   private val comics = sampleComicsObject(3, 2, 4)
   private val state = DetailViewmodel.UiState(character, comics.map { it.imgUrl })

   @Before
   fun setUp() {
      val charactersRepository = buildCharacterRepositoryWith(
         localDataCharacter = sampleCharacters(1, 3, 5),
         localDataComics = comics,
      )
      vm = DetailViewmodel(
         3,
         GetCharacterDetailsUseCase(charactersRepository),
         GetCharacterComicsUseCase(charactersRepository),
         FavoriteToggleUseCase(charactersRepository),
      )
   }

   @Test
   fun `UI is update with the character on start`() = runTest {
      vm.state.test {
         assertEquals(Result.Loading, awaitItem())
         assertEquals(Result.Success(state), awaitItem())
      }
   }

   @Test
   fun `Favorite is updated in local data source`() = runTest {
      vm.state.test {
         assertEquals(Result.Loading, awaitItem())
         assertEquals(Result.Success(state), awaitItem())

         vm.onFavoriteClick()
         runCurrent()

         val newState =
            state.copy(
               character = character.copy(favorite = true),
               message = "Added to favorites"
            )

         assertEquals(Result.Success(newState), awaitItem())
      }
   }
}