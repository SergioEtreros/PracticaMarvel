package com.senkou.practicamarvel.domain.character.usecases

import com.senkou.practicamarvel.test.unit.sampleCharacter
import com.senkou.practicamarvel.test.unit.sampleComics
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class GetCharacterComicsUseCaseTest {

   @Test
   fun `invoke calls repository`() {
      val sampleCharacter = sampleCharacter(4)
      val comicsFlow = flowOf(sampleComics(sampleCharacter.id, 2, 3))

      val useCase = GetCharacterComicsUseCase(mock {
         on { getComicsByCharacterId(4) } doReturn comicsFlow
      })

      val result = useCase(4)

      assertEquals(comicsFlow, result)
   }
}