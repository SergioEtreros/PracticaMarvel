package com.senkou.practicamarvel.ui.home

import app.cash.turbine.test
import com.senkou.practicamarvel.domain.character.usecases.GetCharacterListUseCase
import com.senkou.practicamarvel.test.rules.CoroutinesTestRule
import com.senkou.practicamarvel.test.unit.sampleCharacters
import com.senkou.practicamarvel.ui.common.Result
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class HomeScreenViewmodelTest {

   @get:Rule
   val coroutinesTestRule = CoroutinesTestRule()

   @Mock
   lateinit var getCharacterListUseCase: GetCharacterListUseCase

   private lateinit var vm: HomeScreenViewmodel
   private val characters = sampleCharacters(1, 5, 8)

   @Before
   fun setUp() {
      vm = HomeScreenViewmodel(getCharacterListUseCase)
   }

   @Test
   fun `Characters are requested at start`() = runTest {
      whenever(getCharacterListUseCase()).thenReturn(flowOf(characters))

      vm.state.first()
      runCurrent()

      verify(getCharacterListUseCase, atLeastOnce()).invoke()
   }

   @Test
   fun `Characters are requested`() = runTest {
      whenever(getCharacterListUseCase()).thenReturn(flowOf(characters))

      vm.state.test {
         assertEquals(Result.Loading, awaitItem())
         assertEquals(Result.Success(characters), awaitItem())
      }
   }

   @Test
   fun `Error is propagated when request fails`() = runTest {
      val error = RuntimeException("Something went wrong")
      whenever(getCharacterListUseCase()).thenThrow(error)

      vm.state.test {
         assertEquals(Result.Loading, awaitItem())
         val exceptionMessage = (awaitItem() as Result.Error).throwable.message
         assertEquals("Something went wrong", exceptionMessage)
      }
   }
}