package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.udacity.gradle.builditbigger.network.FetchJokesAsyncTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.s3xy.jokingandroidlibrary.JokeActivity;
import de.s3xy4ngyc.jokes.jokesApi.model.Joke;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements FetchJokesAsyncTask.JokeView {

    @BindView(R.id.adView)
    AdView mAdView;
    @BindView(R.id.btnTellJoke)
    Button mBtnTellJoke;
    @BindView(R.id.loading_indicator)
    ProgressBar mLoadingIndicator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, root);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

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

        if (joke != null) {
            startActivity(JokeActivity.buildIntent(getContext(), joke.getContent()));
        }
    }
}
