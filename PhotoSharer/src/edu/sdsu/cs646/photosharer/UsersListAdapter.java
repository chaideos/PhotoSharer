package edu.sdsu.cs646.photosharer;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 *
 */
public class UsersListAdapter extends BaseAdapter {

    private final Context mContext;

    private final List<String> users;

    public UsersListAdapter(Context context, List<String> users) {
	mContext = context;
	this.users = users;
    }

    // Using the ViewHolder/ViewWrapper pattern as discussed by Mark Murphy in
    // this post
    // http://www.androidguys.com/2008/07/22/fancy-listviews-part-three/
    class ViewHolder {
	TextView text = null;

	ImageView image = null;

	ViewHolder(ImageView image, TextView text) {
	    this.image = image;
	    this.text = text;
	}

	ImageView getImage() {
	    return image;
	}

	TextView getText() {
	    return text;
	}
    }

    @Override
    public int getCount() {

	return users.size();
    }

    @Override
    public Object getItem(int position) {
	return position;
    }

    @Override
    public long getItemId(int position) {
	return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

	ViewHolder holder = null;
	if (convertView == null) {
	    convertView = LayoutInflater.from(mContext).inflate(
		    R.layout.list_item_row, null);
	    TextView textView = (TextView) convertView
		    .findViewById(R.id.userText);
	    ImageView imageView = (ImageView) convertView
		    .findViewById(R.id.userImage);
	    holder = new ViewHolder(imageView, textView);
	    convertView.setTag(holder);
	} else {
	    holder = (ViewHolder) convertView.getTag();
	}
	TextView textView = holder.getText();
	textView.setText(users.get(position));
	return convertView;
    }
}
