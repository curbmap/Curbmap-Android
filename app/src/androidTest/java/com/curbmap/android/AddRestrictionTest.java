package com.curbmap.android;

import android.content.Intent;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddRestrictionTest {
    @Rule
    public ActivityTestRule mActivityRule =
            new ActivityTestRule(MainActivity.class, false, false) {
            };

    @Before
    public void initActivity() {
        mActivityRule.launchActivity(new Intent());
    }

    @Test
    //custom type: festival, weekends, head in
    public void custom() throws Exception {
        pauseTestFor(500);
        onView(withContentDescription("Google Map")).perform(click());
        onView(withId(R.id.addRestrictionButton)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.customTypeLabel)).perform(scrollTo(), click());
        onView(withId(R.id.customTypeText)).perform(scrollTo(), typeText("Festival"));
        closeSoftKeyboard();
        onView(withId(R.id.sunday)).perform(scrollTo(), click());
        onView(withId(R.id.saturday)).perform(scrollTo(), click());
        onView(withId(R.id.allDay)).perform(scrollTo(), click());
        onView(withId(R.id.headIn)).perform(scrollTo(), click());
        onView(withId(R.id.submitButton)).perform(click());
        pauseTestFor(500);
    }

    @Test
    //no parking, 20 minutes, monday, from 4 to 5, angle_angled
    public void addFromTo() throws Exception {
        pauseTestFor(500);
        onView(withContentDescription("Google Map")).perform(click());
        onView(withId(R.id.addRestrictionButton)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.noParking)).perform(scrollTo(), click());
        onView(withId(R.id.timeLimitNumber)).perform(scrollTo(), typeText(String.valueOf("20")));
        closeSoftKeyboard();
        onView(withId(R.id.monday)).perform(scrollTo(), click());
        onView(withId(R.id.withinHoursOf)).perform(scrollTo(), click());
        onView(allOf(withId(R.id.fromTime))).perform(replaceText("4:0"));
        onView(allOf(withId(R.id.toTime))).perform(replaceText("5:0"));
        onView(withId(R.id.angled)).perform(scrollTo(), click());
        onView(withId(R.id.submitButton)).perform(click());
        pauseTestFor(500);
    }

    @Test
    //time limit parking, 20 minutes, sunday, all day, angle_parallel
    public void addAllDay() throws Exception {
        pauseTestFor(500);
        onView(withContentDescription("Google Map")).perform(click());
        onView(withId(R.id.addRestrictionButton)).perform(click());
        pauseTestFor(500);
        onView(withId(R.id.timeLimitParking)).perform(scrollTo(), click());
        onView(withId(R.id.timeLimitNumber)).perform(scrollTo(), typeText(String.valueOf("20")));
        closeSoftKeyboard();
        onView(withId(R.id.sunday)).perform(scrollTo(), click());
        onView(withId(R.id.allDay)).perform(scrollTo(), click());
        onView(withId(R.id.parallel)).perform(scrollTo(), click());
        onView(withId(R.id.submitButton)).perform(click());
        pauseTestFor(500);
    }

    private void pauseTestFor(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

