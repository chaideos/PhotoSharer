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

/**
 * A custom list adapter displaying the users separated by headers of their
 * starting letters. This code has been adapted from the following references
 * Jeff Sharkey's SeperatedListAdapter
 * [http://jsharkey.org/blog/2008/08/18/separating-lists-with
 * -headers-in-android-09/] MergeAdapter provided by CommonsWare [https://github
 * .com/commonsguy/cwac-merge/blob/master/merge/src/com/commonsware
 * /cwac/merge/MergeAdapter.java] and Cyril Mottier's Sectioned List View
 * [http://www.cyrilmottier.com/2010/04/08/astuce-7-creer-des-listes-a-cellules-
 * variees/]
 */
public class UsersListAdapter extends BaseAdapter {

    public final static int TYPE_SECTION_HEADER = 0;

    public final static int TOTAL_VIEWS = 2;

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

    //
    // /**
    // * Returns the number of types of Views that will be created by getView().
    // * We have one view for sections containing starting letters of the Users
    // * and one to display a pic and name of the user.
    // */
    // @Override
    // public int getViewTypeCount() {
    //
    // return TOTAL_VIEWS;
    // }
    //
    // /**
    // * Get the type of View that will be created by getView() for the
    // specified
    // * item.
    // *
    // * @param position
    // * Position of the item whose data we want
    // */
    // @Override
    // public int getItemViewType(int position) {
    //
    // return 2;
    // }
    //
    // /**
    // * Determines if the list item is selectable/clickable.
    // */
    // @Override
    // public boolean areAllItemsEnabled() {
    // return false;
    // }
    //
    // /**
    // * Returns true if the item at the specified position is not a separator.
    // *
    // * @param position
    // * Position of the item whose data we want
    // */
    // @Override
    // public boolean isEnabled(int position) {
    // return false;
    // }

    public List<String> getData() {
	return users;
    }
}
