package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.R;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.fragments.HistoryFragment;

public class HistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.history_container, HistoryFragment.newInstance())
                    .commitNow();
        }
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
            Intent homeIntent = new Intent(HistoryActivity.this, HomeActivity.class);
            this.finish();
            startActivity(homeIntent);
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }
}
