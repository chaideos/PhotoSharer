package edu.sdsu.cs646.photosharer;

import org.apache.http.client.HttpClient;

import android.app.Activity;
import android.net.http.AndroidHttpClient;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

public class UsersActivity extends Activity {

    /**
     * A reference to the listview which will contain the list of users
     */
    private ListView userList;

    HttpClient httpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	userList = (ListView) findViewById(R.id.usersList);
    }

    @Override
    protected void onResume() {
	super.onResume();
	httpClient = AndroidHttpClient.newInstance("Sample Client");
	new RetrieveUsersTask(UsersActivity.this, httpClient, userList)
		.execute("http://bismarck.sdsu.edu/photoserver/userlist");
    }

    @Override
    protected void onPause() {
	super.onPause();
	httpClient.getConnectionManager().shutdown();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.main, menu);
	return true;
    }

}
