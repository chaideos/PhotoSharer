package edu.sdsu.cs646.photosharer.asynctasks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

import edu.sdsu.cs646.photosharer.data.User;
import edu.sdsu.cs646.photosharer.interfaces.LoadDataListener;

/**
 * An AsyncTask which deals with retrieving the users list from the net.
 */
public class RetrieveUsersTask extends AsyncTask<String, Void, List<User>> {

    private final HttpClient httpClient;

    private final LoadDataListener listener;

    private static final String USER_NAME_KEY = "name";

    private static final String ID_KEY = "id";

    private static final String URL = "/userlist";

    public RetrieveUsersTask(LoadDataListener listener) {
	this.listener = listener;
	this.httpClient = AndroidHttpClient.newInstance("Sample User Agent");
    }

    @Override
    protected List<User> doInBackground(String... urls) {
	List<User> users = new ArrayList<User>();
	ResponseHandler<String> responseHandler = new BasicResponseHandler();
	HttpGet request = new HttpGet(urls[0] + URL);
	try {
	    String response = httpClient.execute(request, responseHandler);
	    JSONArray userData = new JSONArray(response);

	    for (int i = 0; i < userData.length(); i++) {
		JSONObject user = (JSONObject) userData.get(i);
		users.add(new User(user.getString(ID_KEY), user
			.getString(USER_NAME_KEY)));
	    }
	} catch (ClientProtocolException e) {

	} catch (IOException e) {

	} catch (JSONException e) {

	} finally {
	    httpClient.getConnectionManager().shutdown();
	    ((AndroidHttpClient) httpClient).close();
	}
	return users;
    }

    @Override
    protected void onPreExecute() {
	listener.preDataFetch();
    }

    @Override
    protected void onPostExecute(List<User> users) {
	listener.onDataLoadComplete(users);
    }
}
