package util;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;


import presenceapp.com.br.ufc.danielfilho.presenceapp.R;

/**
 * Created by Daniel Filho on 10/29/16.
 */

public class NotificationCreator {

    public static void create(Context context, String message, int icon){
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(icon, message, System.currentTimeMillis());
        manager.notify(R.string.app_name, notification);
    }


}
