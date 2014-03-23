package edu.sdsu.cs646.photosharer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import edu.sdsu.cs646.photosharer.fragments.PhotosFragment;
import edu.sdsu.cs646.photosharer.fragments.UsersFragment;
import edu.sdsu.cs646.photosharer.interfaces.UserSelectedListener;

public class UsersActivity extends Activity implements UserSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_master_detail);
	getFragmentManager().beginTransaction()
		.add(R.id.fragment_container, new UsersFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.main, menu);
	return true;
    }

    @Override
    public void onUserSelected(String user) {
	Intent intent = new Intent();
	intent.setClass(this, UserPhotosActivity.class);
	intent.putExtra(PhotosFragment.USER_KEY, user);
	startActivity(intent);
    }

}
