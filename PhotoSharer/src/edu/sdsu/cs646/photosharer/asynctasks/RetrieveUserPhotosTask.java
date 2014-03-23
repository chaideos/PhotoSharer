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

import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;

import edu.sdsu.cs646.photosharer.data.UserPhoto;
import edu.sdsu.cs646.photosharer.interfaces.LoadDataListener;

/**
 * An AsyncTask which deals with retrieving the users list from the net.
 */
public class RetrieveUserPhotosTask extends
	AsyncTask<String, Void, List<UserPhoto>> {

    private final HttpClient httpClient;

    private final LoadDataListener<UserPhoto> listener;

    private static final String PHOTO_NAME_KEY = "name";

    private static final String ID_KEY = "id";

    private static final String URL = "/userphotos/";

    public RetrieveUserPhotosTask(LoadDataListener<UserPhoto> listener) {
	this.listener = listener;
	this.httpClient = AndroidHttpClient.newInstance("Sample User Agent");
    }

    @Override
    protected List<UserPhoto> doInBackground(String... urls) {
	List<UserPhoto> photos = new ArrayList<UserPhoto>();
	ResponseHandler<String> responseHandler = new BasicResponseHandler();
	String userId = urls[1];
	HttpGet request = new HttpGet(urls[0] + URL + userId);
	try {
	    String response = httpClient.execute(request, responseHandler);
	    JSONArray photoData = new JSONArray(response);

	    for (int i = 0; i < photoData.length(); i++) {
		JSONObject photo = (JSONObject) photoData.get(i);
		photos.add(new UserPhoto(photo.getString(ID_KEY), photo
			.getString(PHOTO_NAME_KEY)));
	    }
	    Collections.sort(photos);
	} catch (ClientProtocolException e) {

	} catch (IOException e) {

	} catch (JSONException e) {

	} finally {
	    httpClient.getConnectionManager().shutdown();
	    ((AndroidHttpClient) httpClient).close();
	}
	return photos;
    }

    @Override
    protected void onPreExecute() {
	listener.preDataFetch();
    }

    @Override
    protected void onPostExecute(List<UserPhoto> photos) {
	listener.onDataLoadComplete(photos);
    }
}
