package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.R;
import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.fragments.AddressFragment;

public class AddressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.address_container, AddressFragment.newInstance())
                    .commitNow();
        }
    }
    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
            Intent homeIntent = new Intent(AddressActivity.this, HomeActivity.class);
            this.finish();
            startActivity(homeIntent);
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }
}
