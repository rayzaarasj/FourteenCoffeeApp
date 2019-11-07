package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.fragments.OrderFragment;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.order_container, OrderFragment.newInstance())
                    .commitNow();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent homeIntent = new Intent(OrderActivity.this, HomeActivity.class);
        this.finish();
        startActivity(homeIntent);
    }
}
