package com.senkou.practicamarvel.domain.character.usecases

import com.senkou.practicamarvel.test.unit.sampleCharacters
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class GetCharacterListUseCaseTest {

   @Test
   fun `invoke calls repository`() {
      val charactersFlow = flowOf(sampleCharacters(2, 4, 7))
      val useCase = GetCharacterListUseCase(mock {
         on { characters } doReturn charactersFlow
      })

      val result = useCase()

      assertEquals(charactersFlow, result)
   }
}