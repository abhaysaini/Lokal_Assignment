package com.example.lokal_assignment.viewModels

import android.support.test.rule.ActivityTestRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.lokal_assignment.ui.main.MainActivity
import com.example.lokal_assignment.R
import org.junit.Rule
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class HomeViewModelTest {

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testUIRefreshCryptoCurrency() {
        onView(withId(R.id.swipeRefreshLayout)).perform(swipeDown())
    }
}
