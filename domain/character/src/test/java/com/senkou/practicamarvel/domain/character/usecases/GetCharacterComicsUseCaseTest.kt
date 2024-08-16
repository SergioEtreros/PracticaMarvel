package com.senkou.practicamarvel.domain.character.usecases

import com.senkou.practicamarvel.test.unit.sampleCharacter
import com.senkou.practicamarvel.test.unit.sampleComics
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class GetCharacterComicsUseCaseTest {

   @Test
   fun `invoke calls repository`(): Unit = runBlocking {
      val sampleCharacter = sampleCharacter(4)
      val comics = sampleComics(sampleCharacter.id, 2, 3)

      val useCase = GetCharacterComicsUseCase(mock {
         onBlocking { getComicsByCharacterId(4) } doReturn comics
      })

      val result = useCase(4)

      assertEquals(comics, result)
   }
}