package presenceapp.com.br.ufc.danielfilho.presenceapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.internal.LinkedTreeMap;


import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import connection.Response;
import connection.ServerRequest;
import constants.Constants;
import interfaces.ServerResponseListener;
import io.realm.Realm;
import io.realm.RealmList;
import models.CheckPresence;
import models.Day;
import models.Team;
import models.User;
import presenceapp.com.br.ufc.danielfilho.presenceapp.R;
import util.AppPreferences;
import util.ListTeamsAdapter;
import presence.PresenceManager;

public class MainActivity extends AppCompatActivity implements ServerResponseListener{

    private AppPreferences preferences;
    private User user;
    private RealmList<Team> teamList;
    private ServerRequest request;
    private ListView listView;
    private ListTeamsAdapter adapter;
    private Realm realm;
    private PresenceManager presenceManager;

    private TextView tvStatusTeams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.request = new ServerRequest(this, this);
        preferences = AppPreferences.getInstance(this);
        this.user = new User();
        this.teamList = new RealmList<Team>();

        listView = (ListView) findViewById(R.id.lv_teams);
        adapter = new ListTeamsAdapter(this, teamList);
        listView.setAdapter(adapter);

        this.user.setId(preferences.getString(Constants.USER_ID_KEY));

        tvStatusTeams = (TextView) findViewById(R.id.tv_status_teams);

        //Getting all the trainee teams...
        getTraineeTeams();

        this.presenceManager = new PresenceManager(this);


        Realm.init(this);
        this.realm = Realm.getDefaultInstance();

    }

    private void getTraineeTeams(){
        request.get(ServerRequest.GET_TRAINEE_TEAMS, this.user.getId());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_logout:
                preferences.deleteAllData();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);

            default:
                return super.onOptionsItemSelected(item);
        }
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
                        t.setDistance(Double.parseDouble(mp.get("distance").toString()));
                        t.setPercent(Double.parseDouble(mp.get("percent").toString()));

                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                        Date init = format.parse(mp.get("date_init").toString());
                        Date end = format.parse(mp.get("date_end").toString());
                        t.setDateInit(init);
                        t.setDateEnd(end);

                        RealmList<Day> listDays = new RealmList<Day>();
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
                            day.setIdDay(numberFormat.parse(date.get("id").toString().charAt(0)+"").intValue());

                            List<LinkedTreeMap<String, Object>> checks = (List<LinkedTreeMap<String, Object>>) d.get("check_presence");

                            RealmList<CheckPresence> checkPresenceList = new RealmList<CheckPresence>();
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
                    if(teamList.size() > 0) {
                        //Updating the list view
                        adapter.notifyDataSetChanged();
                        tvStatusTeams.setText("Suas Turmas");

                        //Updating the users teams
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(teamList);
                        realm.commitTransaction();

                        presenceManager.schedulePresences();

                    }
                }catch (Exception e){
                    e.printStackTrace();
                    Log.e("LOG", "LOL--> "+e.toString());
                }
                Log.i("LOG", teamList.toString());
            }else{
                Toast.makeText(this, "Não foi possível atualizar as suas turmas", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onFailure(Response response, String requestUrl) {

    }
}
