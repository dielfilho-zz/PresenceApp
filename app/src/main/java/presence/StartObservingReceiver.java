package presence;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import constants.Constants;
import danielfilho.ufc.br.com.predetect.constants.PredectConstants;
import danielfilho.ufc.br.com.predetect.services.NetworkObserverService;

/**
 * Created by Daniel Filho on 11/11/16.
 */

public class StartObservingReceiver extends WakefulBroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        intent.setAction(PredectConstants.ACTION_SERVICE);
        Intent myIntent = new Intent(context, NetworkObserverService.class);
        myIntent.putExtra(PredectConstants.WIFI_BUNDLE, intent.getByteArrayExtra(PredectConstants.WIFI_BUNDLE));
        startWakefulService(context, myIntent);
    }
}
