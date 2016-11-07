package presence;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import constants.Constants;
import danielfilho.ufc.br.com.predetect.constants.PredectConstants;
import danielfilho.ufc.br.com.predetect.datas.WiFiBundle;
import danielfilho.ufc.br.com.predetect.datas.WiFiData;
import danielfilho.ufc.br.com.predetect.managers.NetworkManager;
import danielfilho.ufc.br.com.predetect.services.NetworkObserverService;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import models.CheckPresence;
import models.Day;
import models.PresencePendingIntent;
import models.Team;

/**
 * Created by Daniel Filho on 10/20/16.
 */

public class PresenceManager {

    private Context context;
    private Realm realm;
    private NetworkManager networkManager;

    public PresenceManager(Context context){
        this.context = context;
        Realm.init(context);
        this.realm = Realm.getDefaultInstance();
        this.networkManager = NetworkManager.getInstance();
    }

    public void schedulePresences(){
        realm.beginTransaction();
        RealmResults<Team> teams = realm.where(Team.class).findAll();

        Calendar tempCalendar = Calendar.getInstance();
        tempCalendar.setTimeInMillis(System.currentTimeMillis());

        Calendar calendarHours = Calendar.getInstance();

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        for (Team team : teams){
            for (Day day : team.getDays()){

                int dayOfWeek = tempCalendar.get(Calendar.DAY_OF_WEEK) - 1;

                while (dayOfWeek != day.getIdDay()) {
                    tempCalendar.add(Calendar.DATE, 1);
                    dayOfWeek = tempCalendar.get(Calendar.DAY_OF_WEEK);
                }

                for (CheckPresence cp : day.getCheckPresenceList()) {
                    if (!cp.isScheduled()) {
                        cp.setScheduled(true);
                        try {
                            calendarHours.setTime(format.parse(cp.getTimeInit()));

                            tempCalendar.set(Calendar.HOUR, calendarHours.get(Calendar.HOUR));
                            tempCalendar.set(Calendar.MINUTE, calendarHours.get(Calendar.MINUTE));

                            //Checking if the alarm is late
                            long diff = Calendar.getInstance().getTimeInMillis() - tempCalendar.getTimeInMillis();

                            if(diff > 0){
                                tempCalendar.add(Calendar.DATE, 7);
                            }

                            Log.d("LOG","DATE CHECK PRESENCE ---------------: "+format1.format(tempCalendar.getTime()));

                            Intent intent = new Intent(context, NetworkObserverService.class);

                            List<String> wiFiDatas = new ArrayList<>();
                            wiFiDatas.add(team.getMacAP());

                            Log.d("LOG", "DATAS: -------------------> "+wiFiDatas.size());

                            intent.putExtra(PredectConstants.WIFI_BUNDLE, networkManager.createWiFiBundle(wiFiDatas, cp.getDuration(), team.getDistance()));

                            scheduleCheck(this.context, intent, tempCalendar.getTimeInMillis());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }

                tempCalendar = Calendar.getInstance();
            }
        }

        realm.commitTransaction();
        realm.close();
    }


    public void cancelChecks(){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        realm.beginTransaction();
        RealmResults<PresencePendingIntent> listPendings = realm.where(PresencePendingIntent.class).findAll();
        Intent intent = new Intent(context, NetworkObserverService.class);
        for(PresencePendingIntent presenceIntent : listPendings){
            PendingIntent pendingIntent = PendingIntent.getService(context, (int) presenceIntent.getId(), intent, 0);
            alarmManager.cancel(pendingIntent);
            Log.d("LOG", "CANCELLING ALL ALARMS CHECKS.....");
        }
        listPendings.deleteAllFromRealm();
        realm.commitTransaction();
    }

    public void scheduleCheck(Context context, Intent intent, long timeInMillis){

        Number number = realm.where(PresencePendingIntent.class).max("id");
        long currentId = number != null ? number.longValue() : 0;
        int nextId = (int) (currentId+1);
        PresencePendingIntent presencePendingIntent = new PresencePendingIntent();
        presencePendingIntent.setId(nextId);

        realm.copyToRealm(presencePendingIntent);

        PendingIntent pendingIntent = PendingIntent.getService(context, nextId, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Log.d("LOG", "Setting the alarm");

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timeInMillis,AlarmManager.INTERVAL_DAY * 7, pendingIntent);

    }

}
