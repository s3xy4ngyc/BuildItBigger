package com.udacity.gradle.builditbigger;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import de.s3xy.jokingandroidlibrary.JokeActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * @author Angelo Rüggeberg <s3xy4ngc@googlemail.com>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule<>(
            MainActivity.class);

    @Test
    public void testClickTellJoke() throws Exception {
        //click on our joke button
        onView(withId(R.id.btnTellJoke)).perform(click());
        //Verify that the intent fired contained a Joke
        intending(hasExtraWithKey(JokeActivity.EXTRA_JOKE));
    }
}