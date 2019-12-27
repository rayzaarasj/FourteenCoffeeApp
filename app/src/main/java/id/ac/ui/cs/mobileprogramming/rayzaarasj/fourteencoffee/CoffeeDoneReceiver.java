package id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import id.ac.ui.cs.mobileprogramming.rayzaarasj.fourteencoffee.activity.HistoryActivity;

public class CoffeeDoneReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && intent.getAction().equalsIgnoreCase("COFFEE_DONE")) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            String channelId = "FourteenCoffeeChannelId";

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String name = "FourteenCoffee";
                String description = "FourteenCoffee Notification Channel";
                int importance = NotificationManager.IMPORTANCE_DEFAULT;
                NotificationChannel channel = new NotificationChannel(channelId, name, importance);
                channel.setDescription(description);

                notificationManager.createNotificationChannel(channel);
            }

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, HistoryActivity.class), 0);

            NotificationCompat.Builder notification = new NotificationCompat.Builder(context, channelId)
                    .setSmallIcon(R.mipmap.ic_launcher_foreground)
                    .setContentTitle(context.getResources().getString(R.string.coffee_done))
                    .setContentText(context.getResources().getString(R.string.coffee_done_detail))
                    .setContentIntent(pendingIntent)
                    .setDefaults(Notification.DEFAULT_SOUND)
                    .setAutoCancel(true);

            notificationManager.notify(0, notification.build());
        }
    }
}
