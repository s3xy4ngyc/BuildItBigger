package com.udacity.gradle.builditbigger.network;

import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.BaseJokeFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.s3xy4ngyc.jokes.jokesApi.model.Joke;

import static java.lang.Thread.sleep;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * @author Angelo Rüggeberg <s3xy4ngc@googlemail.com>
 */
@RunWith(AndroidJUnit4.class)
public class FetchJokesAsyncTaskTest {

    @Mock
    BaseJokeFragment mockView;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAsyncTaskGivesJokeToView() throws Exception {
        //Fire of the async task with out mock
        new FetchJokesAsyncTask().execute(mockView);
        //give it some time to do its work
        sleep(5000);
        //Verify showJoke was called with an Joke
        verify(mockView, times(1)).showJoke(any(Joke.class));
    }

}