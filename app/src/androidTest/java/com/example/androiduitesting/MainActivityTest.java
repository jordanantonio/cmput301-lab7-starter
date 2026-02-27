package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> scenario =
            new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void openCity_switchesToShowActivity() {
        addOneCity("Edmonton");
        openCityFromList("Edmonton");

        onView(withId(R.id.button_back)).check(matches(isDisplayed()));
    }

    @Test
    public void openCity_showsSameCityName() {
        addOneCity("Tokyo");
        openCityFromList("Tokyo");

        onView(withId(R.id.text_city_name)).check(matches(withText("Tokyo")));
    }

    @Test
    public void backButton_returnsToMainActivity() {
        addOneCity("Berlin");
        openCityFromList("Berlin");

        onView(withId(R.id.button_back)).perform(click());

        onView(withId(R.id.city_list)).check(matches(isDisplayed()));
    }

    private void addOneCity(String name) {
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(typeText(name), closeSoftKeyboard());
        onView(withId(R.id.button_confirm)).perform(click());
    }

    private void openCityFromList(String name) {
        onData(is(name)).inAdapterView(withId(R.id.city_list)).atPosition(0).perform(click());
    }
}
