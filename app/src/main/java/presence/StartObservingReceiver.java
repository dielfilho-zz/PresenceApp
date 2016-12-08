package presence;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;


import danielfilho.ufc.br.com.predetect.constants.PredectConstants;
import danielfilho.ufc.br.com.predetect.services.NetworkObserverService;


/**
 * Created by Daniel Filho on 11/11/16.
 */

public class StartObservingReceiver extends WakefulBroadcastReceiver{


    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("LOG", "------------------ START OBSERVING RECEIVER ----------------"+intent.getAction());

        Intent observingIntent = new Intent(context, NetworkObserverService.class);
        observingIntent.putExtra(PredectConstants.WIFI_BUNDLE, intent.getByteArrayExtra(PredectConstants.WIFI_BUNDLE));

        Log.d("LOG", "------------------>  "+intent.getByteArrayExtra(PredectConstants.WIFI_BUNDLE));
        startWakefulService(context, observingIntent);

        Log.d("LOG", "------------------ STARTED WAKEFUL RECEIVER ----------------");
    }
}
