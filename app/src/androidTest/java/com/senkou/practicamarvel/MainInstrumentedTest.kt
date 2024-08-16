package com.senkou.practicamarvel

import androidx.annotation.StringRes
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.senkou.practicamarvel.data.server.MockWebServerRule
import com.senkou.practicamarvel.data.server.fromJson
import com.senkou.practicamarvel.ui.characters.R
import com.senkou.practicamarvel.ui.detail.FAVORITE_TEST_TAG
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import okhttp3.mockwebserver.MockResponse
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MainInstrumentedTest {

   @get:Rule(order = 0)
   val hiltRule = HiltAndroidRule(this)

   @get:Rule(order = 1)
   val mockWebServerRule = MockWebServerRule()

   @get:Rule(order = 2)
   val androidComposeRule = createAndroidComposeRule<MainActivity>()

   @Before
   fun setup() {
      mockWebServerRule.server.enqueue(MockResponse().fromJson("marvel_characters.json"))
      hiltRule.inject()
   }

   @OptIn(ExperimentalTestApi::class)
   @Test
   fun click_a_character_goes_to_detail(): Unit = with(androidComposeRule) {

      waitUntilAtLeastOneExists(hasText("Anita Blake"))
      onNodeWithText("Anita Blake").performClick()

      waitUntilAtLeastOneExists(hasTestTag(FAVORITE_TEST_TAG))

      onNodeWithText(getStringResource(R.string.comics)).assertIsDisplayed()
   }
}

private fun getStringResource(@StringRes id: Int): String {
   val ctx = InstrumentationRegistry.getInstrumentation().targetContext
   return ctx.getString(id)
}