package onsite.gloton.com.co.gloton.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.res.ResourcesCompat;

import java.util.Date;
import java.util.LinkedList;

import onsite.gloton.com.co.gloton.MainActivity;
import onsite.gloton.com.co.gloton.R;
import onsite.gloton.com.co.gloton.activity.GalleryActivity;

/**
 * Created by admin on 3/08/17.
 */

public final class Utilities {

    public static void sendNotification(Context context, String msg) {

        Intent intent = new Intent(context, GalleryActivity.class);
        intent.putExtra("Message", msg);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                intent, PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager
                .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(
                context).setSmallIcon(R.drawable.icono)
                .setContentTitle("Glotón").setContentText(msg)
                .setAutoCancel(true).setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());

    }


}
