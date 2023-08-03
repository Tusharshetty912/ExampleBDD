package com.examplebdd.test

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.rule.ActivityTestRule
import com.examplebdd.MainActivity
import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java.en.And
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import junit.framework.TestCase.assertNotNull
import org.junit.Rule
import org.junit.runner.RunWith

@SmallTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @JvmField
    @Rule
    var activityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Rule
    lateinit var activity: MainActivity

    @Before()
    fun setup(){
        activityTestRule.launchActivity(Intent())
        activity = activityTestRule.activity
    }

    @After()
    fun finish(){
        activityTestRule.finishActivity()
    }

    @Given("^I have a login Activity")
    fun I_have_a_login_activity(){
        assertNotNull(activity)
    }

    @When("^I input email \"([^\"]*)\"$")
    fun I_input_email(email: String){
        Espresso.onView(ViewMatchers.withId(com.examplebdd.R.id.emailText)).perform(ViewActions.typeText(email))
    }

    @And("^I input password \"([^\"]*)\"$")
    fun I_input_password(password: String){
        Espresso.onView(ViewMatchers.withId(com.examplebdd.R.id.passwordText)).perform(ViewActions.typeText(password))
        closeSoftKeyboard()
    }

    @And("^I press login button")
    fun I_press_login_button(){
        Espresso.onView(ViewMatchers.withId(com.examplebdd.R.id.loginBtn)).perform(ViewActions.click())
    }

    @Then("^I should see status on the \"([^\"]*)\"$")
    fun I_should_see_status_on_the(status: String){
        Espresso.onView(ViewMatchers.withId(com.examplebdd.R.id.status)).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    status
                )
            ))
    }
}