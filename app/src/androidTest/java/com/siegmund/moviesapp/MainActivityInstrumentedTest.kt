package com.siegmund.moviesapp

import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.siegmund.moviesapp.ui.credits.CreditsActivity
import com.siegmund.moviesapp.ui.details.DetailsActivity
import com.siegmund.moviesapp.ui.main.MainActivity
import com.siegmund.moviesapp.ui.main.MoviesAdapter
import org.junit.After
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityInstrumentedTest {

    @Rule @JvmField var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup() {
        Intents.init()
    }

    @Test
    fun shouldOpenCreditsScreen() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
        onView(withText("Credits")).perform(click())
        intended(hasComponent(CreditsActivity::class.java.name))
    }

    @Test
    fun shouldOpenDetailsScreen() {
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition<MoviesAdapter.MovieViewHolder>(0, click()))
        intended(hasComponent(DetailsActivity::class.java.name))
    }

    @After
    fun teardown() {
        Intents.release()
    }
}
