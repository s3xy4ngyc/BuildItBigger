package com.udacity.gradle.builditbigger.network;

import android.os.AsyncTask;
import android.support.annotation.Nullable;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

import de.s3xy4ngyc.jokes.jokesApi.JokesApi;
import de.s3xy4ngyc.jokes.jokesApi.model.Joke;


/**
 * @author Angelo Rüggeberg <s3xy4ngc@googlemail.com>
 */
public class FetchJokesAsyncTask extends AsyncTask<FetchJokesAsyncTask.JokeView, Void, Joke> {

    private static JokesApi mJokesApi;
    private JokeView mView;

    @Override
    protected Joke doInBackground(JokeView... params) {
        mView = params[0];

        if (mJokesApi == null) {
            mJokesApi = new JokesApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(request -> request.setDisableGZipContent(true))
                    .build();
        }


        try {
            return mJokesApi.tellJoke().execute();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    protected void onPostExecute(@Nullable Joke joke) {
        mView.showJoke(joke);
    }

    public interface JokeView {
        void showJoke(@Nullable Joke joke);
    }
}
