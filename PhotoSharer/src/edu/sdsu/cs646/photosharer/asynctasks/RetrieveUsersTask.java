package edu.sdsu.cs646.photosharer.asynctasks;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import edu.sdsu.cs646.photosharer.R;
import edu.sdsu.cs646.photosharer.uiadapters.UsersListAdapter;

/**
 * An AsyncTask which deals with retrieving the users list from the net.
 */
public class RetrieveUsersTask extends AsyncTask<String, Void, List<String>> {

    private final HttpClient httpClient;

    private final ListView listView;

    private final Context mContext;

    private ProgressDialog progressDialog;

    private static final String USER_NAME_KEY = "name";

    public RetrieveUsersTask(Context context, HttpClient httpClient,
	    ListView listView) {
	mContext = context;
	this.httpClient = httpClient;
	this.listView = listView;
    }

    @Override
    protected List<String> doInBackground(String... urls) {
	List<String> users = new ArrayList<String>();
	ResponseHandler<String> responseHandler = new BasicResponseHandler();
	HttpGet request = new HttpGet(urls[0]);
	try {
	    String response = httpClient.execute(request, responseHandler);
	    JSONArray userData = new JSONArray(response);

	    for (int i = 0; i < userData.length(); i++) {
		JSONObject user = (JSONObject) userData.get(i);
		users.add(user.getString(USER_NAME_KEY));
	    }
	    Collections.sort(users);
	} catch (ClientProtocolException e) {

	} catch (IOException e) {

	} catch (JSONException e) {

	}
	return users;
    }

    @Override
    protected void onPreExecute() {
	progressDialog = new ProgressDialog(mContext);
	progressDialog.setTitle(mContext.getResources().getString(
		R.string.retrieving_users_str));
	progressDialog.setIndeterminate(true);
	progressDialog.show();
    }

    @Override
    protected void onPostExecute(List<String> users) {
	UsersListAdapter adapter = new UsersListAdapter(mContext, users);
	listView.setAdapter(adapter);
	progressDialog.dismiss();
    }
}
