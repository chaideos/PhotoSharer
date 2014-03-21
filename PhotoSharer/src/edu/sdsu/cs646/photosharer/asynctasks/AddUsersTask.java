package edu.sdsu.cs646.photosharer.asynctasks;

import android.content.Context;
import android.os.AsyncTask;

import edu.sdsu.cs646.photosharer.databases.DatabaseHelper;

/**
 * An AsyncTask which deals with adding the users to a SQLitedatabase.
 */
public class AddUsersTask extends AsyncTask<String, Void, Void> {

    private final DatabaseHelper helper;

    public AddUsersTask(Context context) {
	helper = new DatabaseHelper(context);
    }

    @Override
    protected Void doInBackground(String... users) {
	if (users != null && users.length > 0) {
	    for (String user : users) {
		helper.addUser(user);
	    }
	}
	return null;
    }

}
