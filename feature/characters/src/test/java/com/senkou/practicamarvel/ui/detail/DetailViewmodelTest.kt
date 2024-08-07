package com.senkou.practicamarvel.ui.detail

import app.cash.turbine.test
import com.senkou.practicamarvel.domain.character.usecases.FavoriteToggleUseCase
import com.senkou.practicamarvel.domain.character.usecases.GetCharacterComicsUseCase
import com.senkou.practicamarvel.domain.character.usecases.GetCharacterDetailsUseCase
import com.senkou.practicamarvel.test.rules.CoroutinesTestRule
import com.senkou.practicamarvel.test.unit.sampleCharacter
import com.senkou.practicamarvel.test.unit.sampleComics
import com.senkou.practicamarvel.ui.common.Result
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class DetailViewmodelTest {

   @get:Rule
   val coroutinesTestRule = CoroutinesTestRule()

   @Mock
   lateinit var getCharacterDetailsUseCase: GetCharacterDetailsUseCase

   @Mock
   lateinit var getCharacterComicsUseCase: GetCharacterComicsUseCase

   @Mock
   lateinit var favoriteToggleUseCase: FavoriteToggleUseCase

   private lateinit var vm: DetailViewmodel

   private val character = sampleCharacter(3)
   private val comics = sampleComics(character.id, 2, 3)
   private val state = DetailViewmodel.UiState(character, comics)

   @Before
   fun setUp() {
      whenever(getCharacterDetailsUseCase(3)).thenReturn(flowOf(character))
      whenever(getCharacterComicsUseCase(3)).thenReturn(flowOf(comics))
      vm = DetailViewmodel(
         3,
         getCharacterDetailsUseCase,
         getCharacterComicsUseCase,
         favoriteToggleUseCase
      )
   }

   @Test
   fun `UI is updated with the movie on start`() = runTest {

      vm.state.test {
         assertEquals(Result.Loading, awaitItem())
         assertEquals(Result.Success(state), awaitItem())
      }
   }

   @Test
   fun `Favorite action calls corresponding use case`() =
      runTest(coroutinesTestRule.testDispatcher) {
         vm.state.test {
            assertEquals(Result.Loading, awaitItem())
            assertEquals(Result.Success(state), awaitItem())

            vm.onFavoriteClick()
            runCurrent()

            verify(favoriteToggleUseCase).invoke(any())
            cancelAndIgnoreRemainingEvents()
         }
      }
}