package edu.sdsu.cs646.photosharer;

import org.apache.http.client.HttpClient;

import android.app.Activity;
import android.net.http.AndroidHttpClient;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import edu.sdsu.cs646.photosharer.asynctasks.RetrieveUsersTask;
import edu.sdsu.cs646.photosharer.databases.DatabaseHelper;
import edu.sdsu.cs646.photosharer.uiadapters.UsersListAdapter;

public class UsersActivity extends Activity {

    /**
     * A reference to the listview which will contain the list of users
     */
    private ListView userList;

    /**
     * A reference to the HttpClient used to make requests.
     */
    HttpClient httpClient;

    /**
     * A reference to the database helper class used to perform db operations.
     */
    DatabaseHelper dbHelper;

    /**
     * Indicates the number of users in the database.
     */
    long numOfUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	userList = (ListView) findViewById(R.id.usersList);
	dbHelper = new DatabaseHelper(this);
	numOfUsers = dbHelper.numOfUsers();
    }

    @Override
    protected void onResume() {
	super.onResume();
	if (numOfUsers <= 0) {
	    httpClient = AndroidHttpClient.newInstance("Sample Client");
	    new RetrieveUsersTask(UsersActivity.this, httpClient, userList)
		    .execute("http://bismarck.sdsu.edu/photoserver/userlist");
	} else {
	    UsersListAdapter adapter = new UsersListAdapter(this,
		    dbHelper.getUsers());
	    userList.setAdapter(adapter);
	}
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
