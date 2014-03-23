package edu.sdsu.cs646.photosharer;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * An activity which displays a user photo.
 */
public class DisplayPhotoActivity extends Activity {

    public static final String PHOTO_KEY = "photoLink";

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.display_photo);
	RequestQueue queue = Volley.newRequestQueue(this);
	image = (ImageView) findViewById(R.id.userPhoto);
	String url = getIntent().getStringExtra(DisplayPhotoActivity.PHOTO_KEY);
	ImageRequest request = new ImageRequest(url,
		new Response.Listener<Bitmap>() {
		    @Override
		    public void onResponse(Bitmap response) {
			image.setImageBitmap(response);
		    }
		}, 0, 0, null, null);
	queue.add(request);
    }
}
