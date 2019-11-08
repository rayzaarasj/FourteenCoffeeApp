package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.amitshekhar.DebugDB;

import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.fragments.HomeFragment;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.home_container, HomeFragment.newInstance())
                    .commitNow();
        }
    }
}
