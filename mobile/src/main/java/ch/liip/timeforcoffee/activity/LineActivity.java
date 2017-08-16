package ch.liip.timeforcoffee.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import ch.liip.timeforcoffee.R;
import ch.liip.timeforcoffee.fragment.LineFragment;
import ch.liip.timeforcoffee.fragment.dummy.DummyContent;

public class LineActivity extends AppCompatActivity implements LineFragment.Callbacks {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check whether we're recreating a previously destroyed instance
        if (savedInstanceState != null) {
            // FIXME check how this behaves.
            //we don't want to restore this activity => go back to default activity
            navigateUpTo(new Intent(this, MainActivity.class));
        }

        setContentView(R.layout.activity_line);
    }

    @Override
    public void onRefresh(DummyContent.DummyItem item) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
