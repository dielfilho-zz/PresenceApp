package interfaces;

import connection.Response;

/**
 * Created by Daniel Filho on 9/23/16.
 */

public interface ServerResponseListener {
    void onSuccess(Response response, String requestUrl);
    void onFailure(Response response, String requestUrl);
}
