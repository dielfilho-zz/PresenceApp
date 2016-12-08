package presence;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;
import com.elvishew.xlog.printer.file.FilePrinter;

import java.util.List;

import danielfilho.ufc.br.com.predetect.constants.PredectConstants;
import danielfilho.ufc.br.com.predetect.datas.WiFiBundle;
import danielfilho.ufc.br.com.predetect.datas.WiFiData;
import models.CheckPresence;
import presenceapp.com.br.ufc.danielfilho.presenceapp.R;
import services.SendPresenceService;
import util.NotificationCreator;

/**
 * Created by Daniel Filho on 10/25/16.
 */

public class PresenceReceiver extends WakefulBroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(PredectConstants.ACTION_OBSERVING_ENDS)) {
            Bundle bundle = intent.getBundleExtra(PredectConstants.BUNDLE_FINISH_OBSERVING);
            Log.d("LOG", "ON RECEIVE SEND PRESENCE....");

            try {
                XLog.init(LogLevel.ALL, new FilePrinter.Builder(PredectConstants.LOG_PATH + "presence").build());
            } catch (Exception e) {
            }


            XLog.d(System.currentTimeMillis()+"|  ------------ ON RECEIVE SEND PRESENCE ------------");

            if (bundle != null) {

                List<WiFiData> wiFiDataList = bundle.getParcelableArrayList(PredectConstants.WIFI_SCANNED);
                if(wiFiDataList != null){
                    NotificationCreator.create(context, "Presence: "+wiFiDataList.get(0).getPercent(), R.drawable.wifilogo);
                }

                Log.d("LOG", "TRYING TO START THE SERVICE.....");
                XLog.d(System.currentTimeMillis()+"|  ------------ STARTTING THE SERVICE SEND PRESENCE ------------");
                Intent intentService = new Intent(context, SendPresenceService.class);
                intentService.putExtra(PredectConstants.WIFI_BUNDLE, bundle);
                startWakefulService(context, intentService);
            } else {
                Log.d("LOG", "PRESENCE RECEIVER BUNDLE NULL -------------");
                XLog.d(System.currentTimeMillis()+"|  ------------ PRESENCE BUNDLE ID NULL------------");
            }
        }
    }
}
