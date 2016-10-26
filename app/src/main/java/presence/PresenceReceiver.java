package presence;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import danielfilho.ufc.br.com.predetect.constants.PredectConstants;
import danielfilho.ufc.br.com.predetect.datas.WiFiData;

/**
 * Created by Daniel Filho on 10/25/16.
 */

public class PresenceReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getBundleExtra(PredectConstants.BUNDLE_FINISH_OBSERVING);
        if(bundle != null){
            List<WiFiData> datas = bundle.getParcelableArrayList(PredectConstants.WIFI_SCANNED);
            for(WiFiData w : datas)
                Log.d("LOG", w.getSSID()+" - "+w.getSSID()+" - "+w.getPercent());
        }
    }
}
