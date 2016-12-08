package presenceapp.com.br.ufc.danielfilho.presenceapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import constants.Constants;
import models.Presence;
import presenceapp.com.br.ufc.danielfilho.presenceapp.R;
import util.AppPreferences;

public class PresenceDetailsActivity extends AppCompatActivity {

    private TextView tvTeamName;
    private TextView tvTraineeName;
    private TextView tvLastCheck;
    private TextView tvConfirmed;
    private TextView tvWeekDay;
    private TextView tvAverage;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_presence_details);

        this.tvTeamName = (TextView) findViewById(R.id.tv_teamName);
        this.tvTraineeName = (TextView) findViewById(R.id.tv_traineeName);
        this.tvLastCheck = (TextView) findViewById(R.id.tv_lastCheck);
        this.tvConfirmed = (TextView) findViewById(R.id.tv_confirmed);
        this.tvWeekDay = (TextView) findViewById(R.id.tv_dayOfWeek);
        this.tvAverage = (TextView) findViewById(R.id.tv_avg);

        Bundle myBundle = getIntent().getBundleExtra("BUNDLE_PRESENCE_DETAILS");

        AppPreferences preferences = AppPreferences.getInstance(this);

        tvTeamName.setText(myBundle.getString("TEAM_ID"));
        tvTraineeName.setText(preferences.getString(Constants.USER_NAME));
        tvWeekDay.setText(myBundle.getString("WEEK_DAY"));
        tvAverage.setText(myBundle.getString("AVERAGE"));

        String lastCheck = myBundle.getBoolean("LAST_CHECK") ? "SIM" : "NÃO";
        String confirmed = myBundle.getBoolean("VALID") ? "SIM" : "NÃO";

        tvLastCheck.setText(lastCheck);
        tvConfirmed.setText(confirmed);

    }
}
