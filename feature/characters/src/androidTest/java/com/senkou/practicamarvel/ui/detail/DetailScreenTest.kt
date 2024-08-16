package com.senkou.practicamarvel.ui.detail

import androidx.annotation.StringRes
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.senkou.practicamarvel.test.unit.sampleCharacter
import com.senkou.practicamarvel.test.unit.sampleComicsObject
import com.senkou.practicamarvel.ui.characters.R
import com.senkou.practicamarvel.ui.common.LOADING_INDICATOR_TEST_TAG
import com.senkou.practicamarvel.ui.common.Result
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test

class DetailScreenTest {

   @get:Rule
   val composeTestRule = createComposeRule()

   private val character = sampleCharacter(3)
   private val comics = sampleComicsObject(3, 2, 4)
   private var state = DetailViewmodel.UiState(character, comics.map { it.imgUrl })

   @Test
   fun whenLoadindgState_ShowLoading(): Unit = with(composeTestRule) {
      setContent {
         DetailScreen(
            state = Result.Loading,
            onBack = {},
            onFavortiteClicked = {},
            onMessageShown = {},
         )
      }

      onNodeWithTag(LOADING_INDICATOR_TEST_TAG).assertExists()
   }

   @Test
   fun whenErrorState_ShowError(): Unit = with(composeTestRule) {
      setContent {
         DetailScreen(
            state = Result.Error(RuntimeException("error")),
            onBack = {},
            onFavortiteClicked = {},
            onMessageShown = {},
         )
      }

      onNodeWithText("error").assertExists()
   }

   @Test
   fun whenSuccessState_ShowCharacters(): Unit = with(composeTestRule) {
      setContent {
         DetailScreen(
            state = Result.Success(state),
            onBack = {},
            onFavortiteClicked = {},
            onMessageShown = {},
         )
      }

      onNodeWithText("Name 3").assertExists()
   }

   @Test
   fun whenFavoriteClicked_listenerIsCalled(): Unit = with(composeTestRule) {
      var clicked = false

      setContent {
         DetailScreen(
            state = Result.Success(state),
            onBack = {},
            onFavortiteClicked = { clicked = true },
            onMessageShown = {},
         )
      }

      onNodeWithTag(FAVORITE_TEST_TAG).performClick()

      assertTrue(clicked)
   }

   @Test
   fun whenBackClicked_listenerIsCalled(): Unit = with(composeTestRule) {
      var clicked = false

      setContent {
         DetailScreen(
            state = Result.Success(state),
            onBack = { clicked = true },
            onFavortiteClicked = {},
            onMessageShown = {},
         )
      }

      onNodeWithContentDescription(getStringResource(R.string.back)).performClick()

      assertTrue(clicked)
   }

   @Test
   fun whenFavoriteClicked_messageIsShown(): Unit = with(composeTestRule) {
      var message = ""

      // ESTE NO PASA

      setContent {
         DetailScreen(
            state = Result.Success(state),
            onBack = {},
            onFavortiteClicked = {},
            onMessageShown = {
               message = "mostrado"
            },
         )
      }

      assert(message.isNotEmpty())
   }
}

private fun getStringResource(@StringRes id: Int): String {
   val ctx = InstrumentationRegistry.getInstrumentation().targetContext
   return ctx.getString(id)
}