package connection;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;

import interfaces.ServerResponseListener;

/**
 * Created by Daniel Filho on 9/23/16.
 */

public class ResponseListener implements OptimusHTTP.ResponseListener {

    private Gson gson;
    private ServerResponseListener listener;
    private String requestCode;

    public ResponseListener(ServerResponseListener listener, String requestCode) {
        this.gson = new Gson();
        this.listener = listener;
        this.requestCode = requestCode;
    }


    @Override
    public void onSuccess(String s) {
        Log.i("LOG", s);
        Response res;
        try {
            JsonReader reader = new JsonReader(new StringReader(s));
            reader.setLenient(true);
            res = gson.fromJson(reader, Response.class);
        }catch (Exception e){
            Log.e("LOG", e.toString());
            res = new Response();
            res.setResult(false);
        }
        listener.onSuccess(res, this.requestCode);
    }

    @Override
    public void onFailure(String s) {
        Log.i("LOG", s);
        Response res;
        try{
            JsonReader reader = new JsonReader(new StringReader(s));
            reader.setLenient(true);
            res = gson.fromJson(reader, Response.class);
        }catch (Exception e){
            Log.e("LOG", e.toString());
            res = new Response();
            res.setResult(false);
        }
        listener.onSuccess(res, this.requestCode);
    }
}
