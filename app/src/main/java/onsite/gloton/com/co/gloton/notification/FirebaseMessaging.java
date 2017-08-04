package onsite.gloton.com.co.gloton.notification;


import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import onsite.gloton.com.co.gloton.activity.GalleryActivity;

/**
 * Created by admin on 3/08/17.
 */

public class FirebaseMessaging extends FirebaseMessagingService {

    private static final String TAG = "FCM Service";


    @Override
    public void onMessageReceived(final RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
        Utilities.sendNotification(this, remoteMessage.getNotification().getBody());


    }

}
