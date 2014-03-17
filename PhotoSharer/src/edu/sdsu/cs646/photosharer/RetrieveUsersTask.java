package edu.sdsu.cs646.photosharer;

import java.io.IOException;

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
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 *
 */
public class RetrieveUsersTask extends AsyncTask<String, Void, String> {

    private final HttpClient httpClient;

    private final ListView listView;

    private final Context mContext;

    private ProgressDialog progressDialog;

    public RetrieveUsersTask(Context context, HttpClient httpClient,
	    ListView listView) {
	mContext = context;
	this.httpClient = httpClient;
	this.listView = listView;
    }

    @Override
    protected String doInBackground(String... urls) {
	String response = "";
	ResponseHandler<String> responseHandler = new BasicResponseHandler();
	HttpGet request = new HttpGet(urls[0]);
	try {
	    response = httpClient.execute(request, responseHandler);
	} catch (ClientProtocolException e) {

	} catch (IOException e) {

	}
	return response;
    }

    @Override
    protected void onPreExecute() {
	progressDialog = new ProgressDialog(mContext);
	progressDialog.setTitle("Obtaining Network Data..");
	progressDialog.setIndeterminate(true);
	progressDialog.show();
    }

    @Override
    protected void onPostExecute(String result) {
	try {
	    JSONArray userData = new JSONArray(result);
	    ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
		    mContext, android.R.layout.simple_list_item_1);
	    for (int i = 0; i < userData.length(); i++) {
		JSONObject user = (JSONObject) userData.get(i);
		adapter.add(user.getString("name"));
	    }
	    listView.setAdapter(adapter);
	    progressDialog.dismiss();
	} catch (JSONException e) {

	}
    }
}
