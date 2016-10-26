package util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import models.Team;
import presenceapp.com.br.ufc.danielfilho.presenceapp.R;


/**
 * Created by Daniel Filho on 10/5/16.
 */

public class ListTeamsAdapter extends BaseAdapter {

    private Context context;
    private List<Team> teamList;


    public ListTeamsAdapter(Context context, List<Team> teamList){
        this.context = context;
        this.teamList = teamList;
    }

    @Override
    public int getCount() {
        return teamList.size();
    }

    @Override
    public Object getItem(int position) {
        return teamList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = (View) inflater.inflate(R.layout.item_teams_view, null);

        Team team = teamList.get(position);

        TextView tv = (TextView) v.findViewById(R.id.tv_team_name);
        tv.setText(team.getName());

        return v;
    }
}
