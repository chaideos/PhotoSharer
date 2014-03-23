package edu.sdsu.cs646.photosharer.asynctasks;

import android.content.Context;
import android.os.AsyncTask;

import edu.sdsu.cs646.photosharer.data.UserPhoto;
import edu.sdsu.cs646.photosharer.databases.DatabaseHelper;

/**
 * An AsyncTask which deals with adding the users to a SQLitedatabase.
 */
public class AddPhotosTask extends AsyncTask<UserPhoto, Void, Void> {

    private final DatabaseHelper helper;

    public AddPhotosTask(Context context) {
	helper = new DatabaseHelper(context);
    }

    @Override
    protected Void doInBackground(UserPhoto... photos) {
	if (photos != null && photos.length > 0) {
	    for (UserPhoto photo : photos) {
		helper.addPhoto(photo);
	    }
	}
	return null;
    }

}
