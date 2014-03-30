package edu.sdsu.cs646.photosharer.uiadapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import edu.sdsu.cs646.photosharer.R;
import edu.sdsu.cs646.photosharer.data.User;

/**
 * A custom list adapter displaying the NetworkDatas separated by headers of
 * their starting letters. This code has been adapted from the following
 * references Jeff Sharkey's SeperatedListAdapter
 * [http://jsharkey.org/blog/2008/08/18/separating-lists-with
 * -headers-in-android-09/] MergeAdapter provided by CommonsWare [https://github
 * .com/commonsguy/cwac-merge/blob/master/merge/src/com/commonsware
 * /cwac/merge/MergeAdapter.java], Cyril Mottier's Sectioned List View
 * [http://www.cyrilmottier.com/2010/04/08/astuce-7-creer-des-listes-a-cellules-
 * variees/] and Listview with multiple items tutorial
 * [http://android.amberfog.com/?p=296]
 */
public class UsersListAdapter extends BaseAdapter {

    public final static int TYPE_SEPARATOR = 0;

    public final static int TYPE_ITEM = 1;

    public final static int TOTAL_VIEWS = 2;

    private final Context mContext;

    private final List<Object> data;

    public UsersListAdapter(Context context, List<Object> data) {
	mContext = context;
	this.data = data;
    }

    // Using the ViewHolder/ViewWrapper pattern as discussed by Mark Murphy in
    // this post
    // http://www.androidguys.com/2008/07/22/fancy-listviews-part-three/
    class ViewHolder {
	TextView text = null;

	ImageView image = null;

	ViewHolder(TextView text, ImageView image) {
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
	return data.size();
    }

    @Override
    public Object getItem(int position) {
	return data.get(position);
    }

    @Override
    public long getItemId(int position) {
	return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

	ViewHolder holder = null;
	TextView text = null;
	ImageView image = null;
	int type = getItemViewType(position);
	if (convertView == null) {

	    if (type == TYPE_ITEM) {
		convertView = LayoutInflater.from(mContext).inflate(
			R.layout.list_item_row, null);
		text = (TextView) convertView.findViewById(R.id.dataText);
		image = (ImageView) convertView.findViewById(R.id.dataImage);
	    } else if (type == TYPE_SEPARATOR) {
		convertView = LayoutInflater.from(mContext).inflate(
			R.layout.list_header, null);
		text = (TextView) convertView.findViewById(R.id.sectionHeader);

	    }
	    holder = new ViewHolder(text, image);
	    convertView.setTag(holder);
	} else {
	    holder = (ViewHolder) convertView.getTag();
	}
	TextView textView = holder.getText();
	if (type == TYPE_ITEM) {
	    textView.setText(((User) data.get(position)).getName());
	} else {
	    textView.setText(((String) data.get(position)));
	}
	return convertView;
    }

    @Override
    public int getItemViewType(int position) {
	return (data.get(position) instanceof String) ? TYPE_SEPARATOR
		: TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
	return TOTAL_VIEWS;
    }

    @Override
    public boolean isEnabled(int position) {
	return getItemViewType(position) != TYPE_SEPARATOR;
    }
}
