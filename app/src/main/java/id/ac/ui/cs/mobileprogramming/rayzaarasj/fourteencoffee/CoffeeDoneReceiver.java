package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class CoffeeDoneReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("DEBUGGER", "Broad get");
        if (intent.getAction() != null && intent.getAction().equalsIgnoreCase("COFFEE_DONE")) {
            Toast.makeText(context, R.string.coffee_done, Toast.LENGTH_SHORT).show();
        }
    }
}
