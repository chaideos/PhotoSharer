package edu.sdsu.cs646.photosharer.asynctasks;

import android.content.Context;
import android.os.AsyncTask;

import edu.sdsu.cs646.photosharer.data.User;
import edu.sdsu.cs646.photosharer.databases.DatabaseHelper;

/**
 * An AsyncTask which deals with adding the users to a SQLitedatabase.
 */
public class AddUsersTask extends AsyncTask<User, Void, Void> {

    private final DatabaseHelper helper;

    public AddUsersTask(Context context) {
	helper = new DatabaseHelper(context);
    }

    @Override
    protected Void doInBackground(User... users) {
	if (users != null && users.length > 0) {
	    for (User user : users) {
		helper.addUser(user);
	    }
	}
	return null;
    }

}
