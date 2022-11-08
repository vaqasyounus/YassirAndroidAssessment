package com.yassir.task.ui

import android.widget.Toast
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasClassName
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import com.yassir.task.R
import com.yassir.task.ui.detail.DetailActivity
import com.yassir.task.ui.movies.MoviesActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Thread.sleep


/**
 * Created by AhmedEltaher
 */
@HiltAndroidTest
class DetailActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var mActivityTestRule = IntentsTestRule(DetailActivity::class.java, true, false)

    @Before
    fun setup() {
    }

    @Test
    fun testUI() {
        mActivityTestRule.launchActivity(null)
        onView(withId(R.id.iv_banner)).check(matches(isDisplayed()))
        onView(withId(R.id.detail)).check(matches(isDisplayed()))
        Intents.intended(hasComponent(hasClassName("com.yassir.task.ui.detail.DetailActivity")))
    }

    @After
    fun shutDown() {
    }
}
