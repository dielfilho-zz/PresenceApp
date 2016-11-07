package util;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by Daniel Filho on 11/5/16.
 */

public class ToastUiThread {

    private Activity activity;

    public ToastUiThread(Activity activity) {
        this.activity = activity;
    }

    public void showToastOnUi(final String message, final int timeOut){
        Runnable toastRun = new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity.getApplicationContext(), message, timeOut).show();
            }
        };

        activity.runOnUiThread(toastRun);
    }

}
