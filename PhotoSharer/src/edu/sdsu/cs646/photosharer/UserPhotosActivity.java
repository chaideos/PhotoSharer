package edu.sdsu.cs646.photosharer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import edu.sdsu.cs646.photosharer.fragments.PhotosFragment;
import edu.sdsu.cs646.photosharer.interfaces.PhotoSelectionListener;

/**
 * An activity which acts as a host for the {@link PhotosFragment}
 */
public class UserPhotosActivity extends Activity implements
	PhotoSelectionListener {

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
    public void onPhotoSelected(String photo) {
	Intent intent = new Intent();
	intent.setClass(this, DisplayPhotoActivity.class);
	intent.putExtra(DisplayPhotoActivity.PHOTO_KEY, photo);
	startActivity(intent);
    }
}
