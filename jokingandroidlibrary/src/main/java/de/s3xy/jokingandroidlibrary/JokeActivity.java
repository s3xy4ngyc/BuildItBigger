package de.s3xy.jokingandroidlibrary;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    public static final String EXTRA_JOKE = "extra_joke";

    public static Intent buildIntent(Context context, String joke) {
        Intent intent = new Intent(context, JokeActivity.class);
        intent.putExtra(EXTRA_JOKE, joke);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        checkArguments(getIntent().getExtras());

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(getIntent().getExtras().getString(EXTRA_JOKE));

    }

    private void checkArguments(Bundle arguments) throws IllegalArgumentException {
        if (TextUtils.isEmpty(arguments.getString(EXTRA_JOKE))) {
            throw new IllegalArgumentException("Activity must be launched with EXTRA_JOKE and a valid value.");
        }
    }
}
