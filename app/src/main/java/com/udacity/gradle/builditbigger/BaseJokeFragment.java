package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.udacity.gradle.builditbigger.network.FetchJokesAsyncTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.s3xy.jokingandroidlibrary.JokeActivity;
import de.s3xy4ngyc.jokes.jokesApi.model.Joke;


/**
 * A fragment containing a button that will launch a new activity containing a joke
 */
public class BaseJokeFragment extends Fragment implements FetchJokesAsyncTask.JokeView {

    @BindView(R.id.btnTellJoke)
    Button mBtnTellJoke;
    @BindView(R.id.loading_indicator)
    ProgressBar mLoadingIndicator;
    @BindView(R.id.fragment_root)
    RelativeLayout mFragmentRoot;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);
        return root;
    }


    @OnClick(R.id.btnTellJoke)
    void tellJoke() {
        fetchJoke();
    }

    protected void fetchJoke() {
        mBtnTellJoke.setVisibility(View.GONE);
        mLoadingIndicator.setVisibility(View.VISIBLE);
        new FetchJokesAsyncTask().execute(this);
    }

    @Override
    public void showJoke(@Nullable Joke joke) {

        mBtnTellJoke.setVisibility(View.VISIBLE);
        mLoadingIndicator.setVisibility(View.GONE);

        if (joke == null) {
            Snackbar.make(mFragmentRoot, R.string.loading_error, Snackbar.LENGTH_LONG)
                    .setAction(R.string.action_retry, v -> fetchJoke())
                    .show();
        } else
            startActivity(JokeActivity.buildIntent(getContext(), joke.getContent()));
    }
}
