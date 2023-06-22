package com.gymix.training

import android.content.Intent
import android.widget.Button
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows
import org.junit.Assert

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {

    private var _activity: MainActivity? = null
    private val activity get() = _activity!!

    @Before
    fun setUp() {
        _activity = Robolectric.buildActivity(MainActivity::class.java)
            .setup()
            .get()
    }

    @Test
    fun clickingLogin_shouldStartLoginActivity() {
        activity.findViewById<Button>(R.id.login).performClick()
        val expectedIntent = Intent(activity, GymixActivity::class.java)
        val actual = Shadows.shadowOf(RuntimeEnvironment.getApplication()).nextStartedActivity
        Assert.assertEquals(expectedIntent.component, actual.component)
    }

}