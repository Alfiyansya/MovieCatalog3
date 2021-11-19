package com.alfian.moviecatalog3.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.alfian.moviecatalog3.R
import com.alfian.moviecatalog3.util.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest{

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }
    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun loadMovies(){
        //Movie List
        onView(withId(R.id.rv_show))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(4)
        )
        onView(withId(R.id.rv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(4,
                click()
            ))
        //Detail Movie
        onView(withId(R.id.detail_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_poster))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_image))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_genre))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_rating))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_rating_bar))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_overview_value))
            .check(matches(isDisplayed()))
        onView(withId(R.id.coordinator_layout))
            .perform(ViewActions.swipeUp())
        onView(withId(R.id.detail_fab_favorite))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_fab_favorite))
            .perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        //Movie Favorite
        onView(withId(R.id.btn_favorite)).perform(click())
        onView(withId(R.id.rv_favorite))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                click()
            ))
        //Detail Movie Favorite
        onView(withId(R.id.detail_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_poster))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_image))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_genre))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_rating))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_rating_bar))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_overview_value))
            .check(matches(isDisplayed()))
        onView(withId(R.id.coordinator_layout))
            .perform(ViewActions.swipeUp())
        onView(withId(R.id.detail_fab_favorite))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_fab_favorite))
            .perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
    }

    @Test
    fun loadTvShow(){
        onView(withText("TvShow")).perform(click())
        onView(withId(R.id.rv_show))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(4
            )
        )
        onView(withId(R.id.rv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(4,
                click()
            ))
        //TvShow Detail
        onView(withId(R.id.detail_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_poster))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_image))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_genre))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_rating))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_rating_bar))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_overview_value))
            .check(matches(isDisplayed()))
        onView(withId(R.id.coordinator_layout))
            .perform(ViewActions.swipeUp())
        onView(withId(R.id.detail_fab_favorite))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_fab_favorite))
            .perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())

        //TvShow Favorite
        onView(withId(R.id.btn_favorite)).perform(click())
        onView(withText("TvShow")).perform(click())
        onView(withId(R.id.rv_favorite))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_favorite)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
                click()
            ))
        //Detail TvShow Favorite
        onView(withId(R.id.detail_title))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_poster))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_image))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_genre))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_rating))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_rating_bar))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_overview_value))
            .check(matches(isDisplayed()))
        onView(withId(R.id.coordinator_layout))
            .perform(ViewActions.swipeUp())
        onView(withId(R.id.detail_fab_favorite))
            .check(matches(isDisplayed()))
        onView(withId(R.id.detail_fab_favorite))
            .perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
    }

}