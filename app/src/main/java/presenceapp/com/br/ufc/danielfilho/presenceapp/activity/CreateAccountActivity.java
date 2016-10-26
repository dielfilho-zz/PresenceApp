package presenceapp.com.br.ufc.danielfilho.presenceapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

public class CreateAccountActivity extends AppCompatActivity implements ServerResponseListener {

    private EditText etName;
    private EditText etUser;
    private EditText etPass;
    private EditText etConfirmPass;

    private ServerRequest serverRequest;
    private AppPreferences preferences;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);


        this.preferences = AppPreferences.getInstance(this);

        this.user = new User();

        this.etName = (EditText) findViewById(R.id.et_cacc_name);
        this.etUser = (EditText) findViewById(R.id.et_cacc_user);
        this.etPass = (EditText) findViewById(R.id.et_cacc_pass);
        this.etConfirmPass = (EditText) findViewById(R.id.et_cacc_confirm_pass);

        this.serverRequest = new ServerRequest(this, this);

    }


    public void createAccount(View v){
        Log.i("LOG", user.toString());
        if(checkPass()){
            user.setName(etName.getText().toString());
            user.setUsername(etUser.getText().toString());
            user.setPass(etPass.getText().toString());

            Role role = new Role();

            role.setType("TRAINEE");

            user.setRole(role);

            serverRequest.post(ServerRequest.CREATE_ACCOUNT, user);
        }else{
            Toast.makeText(this, "As senhas não conferem!", Toast.LENGTH_LONG).show();
        }
    }

    private boolean checkPass(){
        return etPass.getText().toString().equals(etConfirmPass.getText().toString());
    }


    @Override
    public void onSuccess(Response response, String requestUrl) {
        if(requestUrl.equals(ServerRequest.CREATE_ACCOUNT)){
            if(response.getResult()){
                if(response.getData() != null){
                    LinkedTreeMap<String, Object> data = (LinkedTreeMap<String, Object>) response.getData();
                    String userId = data.get("_id").toString();
                    String userName = data.get("name").toString();
                    preferences.putString(Constants.USER_ID_KEY, userId);
                    preferences.putString(Constants.USER_NAME, userName);

                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);

                    Toast.makeText(this, "Conta criada com sucesso!", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(this, "Não foi possível criar sua conta!", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onFailure(Response response, String requestUrl) {

    }
}
