package util;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.NotificationCompat;


import java.util.Random;

import presenceapp.com.br.ufc.danielfilho.presenceapp.R;

/**
 * Created by Daniel Filho on 10/29/16.
 */

public class NotificationCreator {

    public static void create(Context context, String message, int icon){
        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setSmallIcon(icon)
                .setContentTitle("PresenceApp")
                .setContentText(message)
                .setGroup("PresenceApp")
                .setStyle(new NotificationCompat.BigTextStyle())
                .setDefaults(Notification.DEFAULT_ALL);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(generateRandomId(), mBuilder.build());
    }


    private static int generateRandomId(){
        Random random = new Random();
        return random.nextInt(9999 - 1000) + 1000;
    }

}
