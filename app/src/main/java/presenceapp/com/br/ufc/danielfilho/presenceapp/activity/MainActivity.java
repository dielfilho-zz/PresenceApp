package presenceapp.com.br.ufc.danielfilho.presenceapp.activity;

import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.internal.LinkedTreeMap;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import connection.Response;
import connection.ServerRequest;
import constants.Constants;
import interfaces.ServerResponseListener;
import models.CheckPresence;
import models.Day;
import models.Team;
import models.User;
import presenceapp.com.br.ufc.danielfilho.presenceapp.R;
import util.AppPreferences;

public class MainActivity extends AppCompatActivity implements ServerResponseListener{

    private AppPreferences preferences;
    private User user;
    private List<Team> teamList;
    private ServerRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.request = new ServerRequest(this, this);
        preferences = AppPreferences.getInstance(this);
        this.user = new User();
        this.teamList = new ArrayList<Team>();
        this.user.set_id(preferences.getString(Constants.USER_ID_KEY));


        //Getting all the trainee teams...
        getTraineeTeams();

    }

    private void getTraineeTeams(){
        request.get(ServerRequest.GET_TRAINEE_TEAMS, this.user.get_id());
    }


    @Override
    public void onSuccess(Response response, String requestUrl) {
        if(requestUrl.equals(ServerRequest.GET_TRAINEE_TEAMS)){
            if(response.getResult()){
                ArrayList<LinkedTreeMap<String, Object>> data = (ArrayList<LinkedTreeMap<String, Object>>) response.getData();
                try {
                    for(LinkedTreeMap<String, Object> mp : data){
                        Team t = new Team();

                        t.setId(mp.get("_id").toString());
                        t.setMacAP(mp.get("mac_ap").toString());
                        t.setName(mp.get("name").toString());

                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

                        Date init = format.parse(mp.get("date_init").toString());
                        Date end = format.parse(mp.get("date_end").toString());
                        t.setDateInit(init);
                        t.setDateEnd(end);

                        List<Day> listDays = new ArrayList<Day>();
                        ArrayList<LinkedTreeMap<String, Object>> days = (ArrayList<LinkedTreeMap<String, Object>>) mp.get("days");
                        for(LinkedTreeMap<String, Object> d : days){
                            Day day = new Day();
                            day.setId(d.get("_id").toString());
                            day.setTimeInit(d.get("time_init").toString());
                            day.setTimeEnd(d.get("time_end").toString());

                            LinkedTreeMap<String, Object> date = (LinkedTreeMap<String, Object>) d.get("date");
                            day.setName(date.get("day_name").toString());

                            NumberFormat numberFormat = NumberFormat.getNumberInstance();
                            numberFormat.setParseIntegerOnly(true);
                            day.setIdDay(numberFormat.parse(date.get("id").toString()).intValue());

                            List<LinkedTreeMap<String, Object>> checks = (List<LinkedTreeMap<String, Object>>) d.get("check_presence");

                            List<CheckPresence> checkPresenceList = new ArrayList<>();
                            for(LinkedTreeMap<String, Object> ch : checks){
                                CheckPresence check = new CheckPresence();
                                check.setDuration(Integer.parseInt(ch.get("duration").toString()));
                                check.setTimeInit(ch.get("time_init").toString());

                                checkPresenceList.add(check);
                            }

                            day.setCheckPresenceList(checkPresenceList);

                            listDays.add(day);
                        }

                        t.setDays(listDays);
                        teamList.add(t);
                    }
                }catch (Exception e){
                    Log.e("LOG", e.toString());
                }
                Log.i("LOG", teamList.toString());
            }else{
                Toast.makeText(this, "Não foi possível carregar as suas turmas", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onFailure(Response response, String requestUrl) {

    }
}
