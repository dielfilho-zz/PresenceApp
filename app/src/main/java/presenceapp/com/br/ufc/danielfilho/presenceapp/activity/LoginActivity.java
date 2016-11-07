package presenceapp.com.br.ufc.danielfilho.presenceapp.activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.internal.LinkedTreeMap;

import connection.Response;
import connection.ServerRequest;
import constants.Constants;
import interfaces.ServerResponseListener;
import models.Role;
import models.User;
import presenceapp.com.br.ufc.danielfilho.presenceapp.R;
import util.AppPreferences;
import util.NotificationCreator;
import util.ToastUiThread;

public class LoginActivity extends AppCompatActivity implements ServerResponseListener {

    private ServerRequest serverRequest;
    private TextView tv_username;
    private TextView tv_pass;


    private ToastUiThread toastUiThread;

    private AppPreferences preferences;

    private User user;
    private Role role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.serverRequest = new ServerRequest(this, this);
        this.role = new Role();
        this.user = new User();


        this.preferences = AppPreferences.getInstance(this);

        //Checking if the user is logged
        if(preferences.getBoolean(Constants.USER_KEEP_LOGGED)){
            callMainActivity();
        }else {
            this.tv_username = (TextView) findViewById(R.id.et_username);
            this.tv_pass = (TextView) findViewById(R.id.et_pass);

            this.toastUiThread = new ToastUiThread(this);
        }
    }


    private void callMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void createAccount(View v){
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    public void checkLogin(View v){
        String username = tv_username.getText().toString();
        String pass = tv_pass.getText().toString();
        role.setType("TRAINEE");
        user.setRole(role);
        user.setUsername(username);

        user.setPass(pass);

        this.serverRequest.post(ServerRequest.LOGIN, user);

    }



    @Override
    public void onSuccess(Response res, String requestUrl) {
        if(requestUrl == ServerRequest.LOGIN){
            if(res.getResult()){
                Log.i("LOG", res.getData().toString());
                LinkedTreeMap<String, Object> r = (LinkedTreeMap<String, Object>) res.getData();

                role.setType("TRAINEE");

                user.setId(r.get("_id").toString());
                user.setName(r.get("name").toString());
                user.setRole(role);

                Toast.makeText(this, "Seja Bem Vindo!", Toast.LENGTH_LONG).show();
                callMainActivity();

                preferences.putString(Constants.USER_ID_KEY, user.getId());
                preferences.putString(Constants.USER_NAME, user.getName());

                //Checking if the users check the "Keep me connected"

                preferences.putBoolean(Constants.USER_KEEP_LOGGED, true);


            }else{
                Toast.makeText(this, "Usuário e/ou Senha Inválidos", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onFailure(Response ob, String requestUrl) {
        Log.d("LOG", "------------------------>"+ob.getResult());
        toastUiThread.showToastOnUi("Ops, não foi possível efetuar o login.", Toast.LENGTH_LONG);
    }
}
