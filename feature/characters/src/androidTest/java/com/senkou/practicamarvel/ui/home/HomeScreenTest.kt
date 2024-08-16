package com.senkou.practicamarvel.ui.home

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.senkou.practicamarvel.test.unit.sampleCharacters
import com.senkou.practicamarvel.ui.common.LOADING_INDICATOR_TEST_TAG
import com.senkou.practicamarvel.ui.common.Result
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

   @get:Rule
   val composeTestRule = createComposeRule()

   private val characters = sampleCharacters(1, 3, 4)

   @Test
   fun whenLoadindgState_ShowLoading(): Unit = with(composeTestRule) {
      setContent {
         HomeScreen(Result.Loading) {}
      }

      onNodeWithTag(LOADING_INDICATOR_TEST_TAG).assertExists()
   }

   @Test
   fun whenErrorState_ShowError(): Unit = with(composeTestRule) {
      setContent {
         HomeScreen(Result.Error(RuntimeException("error"))) {}
      }

      onNodeWithText("error").assertExists()
   }

   @Test
   fun whenSuccessState_ShowCharacters(): Unit = with(composeTestRule) {
      setContent {
         HomeScreen(Result.Success(characters)) {}
      }

      onNodeWithText("Name 3").assertExists()
   }

   @Test
   fun whenCharacterClicked_listenerIsCalled(): Unit = with(composeTestRule) {
      var clickedCharacter = -1

      setContent {
         HomeScreen(Result.Success(characters)) {
            clickedCharacter = it.id
         }
      }

      onNodeWithText("Name 3").performClick()

      assertEquals(3, clickedCharacter)
   }
}