package edu.sdsu.cs646.photosharer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import edu.sdsu.cs646.photosharer.fragments.UsersFragment;

public class UsersActivity extends Activity {

    UsersFragment usersFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.main, menu);
	return true;
    }

}
