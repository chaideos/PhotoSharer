package edu.sdsu.cs646.photosharer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import edu.sdsu.cs646.photosharer.fragments.PhotosFragment;

/**
 * An activity which acts as a host for the {@link PhotosFragment}
 */
public class UserPhotosActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_master_detail);
	String user = getIntent().getStringExtra(PhotosFragment.USER_KEY);
	PhotosFragment fragment = PhotosFragment.newInstance(user);
	getFragmentManager().beginTransaction()
		.add(R.id.fragment_container, fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.main, menu);
	return true;
    }
}
