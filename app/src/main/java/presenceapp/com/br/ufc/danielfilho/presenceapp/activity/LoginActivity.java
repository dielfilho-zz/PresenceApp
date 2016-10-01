package presenceapp.com.br.ufc.danielfilho.presenceapp.activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;

import connection.Response;
import connection.ServerRequest;
import constants.Constants;
import interfaces.ServerResponseListener;
import models.Role;
import models.User;
import presenceapp.com.br.ufc.danielfilho.presenceapp.R;
import util.AppPreferences;

public class LoginActivity extends AppCompatActivity implements ServerResponseListener {

    private ServerRequest serverRequest;
    private TextView tv_username;
    private TextView tv_pass;
    private CheckBox checkBoxKeepLogged;

    private AppPreferences preferences;

    private User user;
    private Role role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.preferences = AppPreferences.getInstance(this);

        //Checking if the user is logged
        if(!preferences.getBoolean(Constants.USER_KEEP_LOGGED)){
            callMainActivity();
        }else {

            this.tv_username = (TextView) findViewById(R.id.et_username);
            this.tv_pass = (TextView) findViewById(R.id.et_pass);
            this.checkBoxKeepLogged = (CheckBox) findViewById(R.id.cb_keepme_logged);


            this.serverRequest = new ServerRequest(this, this);
            this.role = new Role();
            this.user = new User();

            //getting the trainee role
            this.serverRequest.get(ServerRequest.GET_ROLES, null);
        }
    }


    private void callMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    public void checkLogin(View v){
        String username = tv_username.getText().toString();
        String pass = tv_pass.getText().toString();
        user.setRole(role);
        user.setUsername(username);

        user.setPass(pass);

        this.serverRequest.post(ServerRequest.LOGIN, user);

    }



    @Override
    public void onSuccess(Response res, String requestUrl) {
        Log.i("LOG", requestUrl);
        if(requestUrl == ServerRequest.GET_ROLES){
            if(res.getResult()){
                ArrayList<LinkedTreeMap<String, Object>> roles = (ArrayList<LinkedTreeMap<String, Object>>) res.getData();
                for(LinkedTreeMap<String, Object> r : roles){
                    if(r.get("type").toString().equals("TRAINEE")) {
                        role.set_id(r.get("_id").toString());
                        role.setName(r.get("name").toString());
                        role.setType(r.get("type").toString());
                    }
                }
            }else{
                //TODO GET ROLES ERROR
            }
        }else if(requestUrl == ServerRequest.LOGIN){
            if(res.getResult()){
                Log.i("LOG", res.getData().toString());
                LinkedTreeMap<String, Object> r = (LinkedTreeMap<String, Object>) res.getData();

                user.set_id(r.get("_id").toString());
                user.setName(r.get("name").toString());

                Toast.makeText(this, "Seja Bem Vindo!", Toast.LENGTH_LONG).show();
                callMainActivity();

                preferences.putString(Constants.USER_ID_KEY, user.get_id());
                preferences.putString(Constants.USER_NAME, user.getName());

                //Checking if the users check the "Keep me connected"
                if(checkBoxKeepLogged.isChecked()){
                    preferences.putBoolean(Constants.USER_KEEP_LOGGED, true);
                }

            }else{
                Toast.makeText(this, "Usuário e/ou Senha Inválidos", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onFailure(Response ob, String requestUrl) {
        Log.i("LOG", "------------------------>"+ob.getResult());
    }
}
