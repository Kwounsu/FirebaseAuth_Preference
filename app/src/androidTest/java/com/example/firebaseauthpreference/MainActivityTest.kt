package com.example.firebaseauthpreference


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun listGoesOverTheFold() {
        onView(withId(R.id.et_email)).perform(typeText("klee21123@gmail.com"))
        onView(withId(R.id.et_password)).perform(typeText("12345678!")).perform(closeSoftKeyboard())
        onView(withId(R.id.btn_login)).perform(click())
    }
}