package com.example.composeretrofitmvvmdemo

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.composeretrofitmvvmdemo.dao.Movie
import org.junit.Rule
import org.junit.Test

class RecyclerViewTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun movieItemTest() {
        val movieList = listOf<Movie>(
            Movie(
                1,
                "Coco is a 2017 American 3D computer-animated musical fantasy adventure film produced by Pixar",
                "https://howtodoandroid.com/images/coco.jpg",
                "12/12/2000",
                "COCO"
            )
        )


        composeTestRule.setContent {
            MovieList(movieList)
            //MovieItem(movie = movie, onClick = {})
        }
        //Thread.sleep(5000)

        //composeTestRule.onRoot(useUnmergedTree = true).printToLog("TAG")

        composeTestRule.onNodeWithContentDescription("Item_card")
            .assertIsDisplayed()

//        composeTestRule.onNodeWithContentDescription("Item_card")
//            .assertHasClickAction()

//        composeTestRule.onNodeWithContentDescription("Item_card")
//            .performClick()

/*
        composeTestRule.onNodeWithContentDescription("Item_card")
            .assertTextContains("COCO")
            .assertTextContains("12/12/2000")
            .assertIsDisplayed()
            .performClick()
*/

        /*composeTestRule.onNodeWithContentDescription("Item_row")
            .assertIsDisplayed()
            .performClick()*/

       /* composeTestRule.onNodeWithContentDescription("Item_img")
            .assertIsDisplayed()
            .performClick()*/

        composeTestRule.onNodeWithContentDescription("Item_btn")
            .assertIsDisplayed()
            //.performClick()

    }


}