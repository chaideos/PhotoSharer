/**
 * ================================================================================================
 *                                Copyright (C) Hospira, Inc.
 *                                    All rights reserved
 * ================================================================================================
 * Notice:  All Rights Reserved.
 * This material contains the trade secrets and confidential information of Hospira, Inc., which 
 * embody substantial creative effort, ideas and expressions. No part of this material may be 
 * reproduced or transmitted in any form or by any means, electronic, mechanical, optical or 
 * otherwise, including photocopying and recording, or in connection with any information storage 
 * or retrieval system, without written permission from:
 * 
 * Hospira, Inc.
 * 13520 Evening Creek Dr., Suite 200
 * San Diego, CA 92128
 * www.hospira.com
 * ================================================================================================
 */

package edu.sdsu.cs646.photosharer.fragments;

import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.app.ListFragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import edu.sdsu.cs646.photosharer.R;
import edu.sdsu.cs646.photosharer.asynctasks.RetrieveUserPhotosTask;
import edu.sdsu.cs646.photosharer.data.UserPhoto;
import edu.sdsu.cs646.photosharer.databases.DatabaseHelper;
import edu.sdsu.cs646.photosharer.interfaces.LoadDataListener;
import edu.sdsu.cs646.photosharer.interfaces.UserSelectedListener;
import edu.sdsu.cs646.photosharer.uiadapters.NetworkDataListAdapter;

/**
 * A ListFragment depicting the user photos list
 */
public class PhotosFragment extends ListFragment implements
	LoadDataListener<UserPhoto> {

    public static final String USER_KEY = "USER_ID";
    private UserSelectedListener selectionListener;
    /**
     * A reference to the database helper class used to perform db operations.
     */
    DatabaseHelper dbHelper;

    /**
     * Indicates the number of users in the database.
     */
    long numOfPhotos;

    /**
     * Indicates the base URL of the photo server.
     */
    String baseUrl;

    /**
     * Indicates the user whose photos need to be retrieved.
     */
    String userId;

    /**
     * A reference to a ProgressDialog widget
     */
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	baseUrl = getResources().getString(R.string.base_url);
    }

    @Override
    public void onAttach(Activity activity) {
	super.onAttach(activity);

	// This makes sure that the container activity has implemented
	// the callback interface. If not, it throws an exception
	try {
	    selectionListener = (UserSelectedListener) activity;
	} catch (ClassCastException e) {
	    throw new ClassCastException(activity.toString()
		    + " must implement UserSelectedListener");
	}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	    Bundle savedInstanceState) {
	return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
	super.onActivityCreated(savedInstanceState);
	userId = getArguments().getString(USER_KEY);
	dbHelper = new DatabaseHelper(getActivity());
	numOfPhotos = 0;
	if (numOfPhotos <= 0) {
	    new RetrieveUserPhotosTask(this).execute(baseUrl, userId);
	} else {
	    // setAdapter(dbHelper.getUsers());
	}
    }

    @Override
    public void onResume() {
	super.onResume();
    }

    @Override
    public void onPause() {
	super.onPause();
    }

    @Override
    public void onDataLoadComplete(List<UserPhoto> data) {
	if (getActivity() != null) {
	    setAdapter(data);
	    progressDialog.dismiss();
	    UserPhoto[] userArray = new UserPhoto[data.size()];
	    // new AddUsersTask(getActivity()).execute(data.toArray(userArray));
	}
    }

    @Override
    public void preDataFetch() {
	if (getActivity() != null) {
	    progressDialog = new ProgressDialog(getActivity());
	    progressDialog.setTitle(getResources().getString(
		    R.string.retrieving_photos_str));
	    progressDialog.setIndeterminate(true);
	    progressDialog.show();
	}
    }

    @Override
    public void onListItemClick(ListView list, View view, int position, long id) {
	UserPhoto selectedUser = (UserPhoto) getListAdapter().getItem(position);
	selectionListener.onUserSelected(selectedUser.getId());
    }

    private void setAdapter(List<UserPhoto> data) {
	if (getActivity() != null) {
	    Collections.sort(data);
	    ListAdapter adapter = new NetworkDataListAdapter<UserPhoto>(
		    getActivity(), data);
	    setListAdapter(adapter);
	}
    }

    /**
     * @param user
     *            - A user whose photos need to be retrieved
     * @return - PhotosFragment
     */
    public static PhotosFragment newInstance(String user) {
	PhotosFragment fragment = new PhotosFragment();
	Bundle args = new Bundle();
	args.putString(USER_KEY, user);
	fragment.setArguments(args);
	return fragment;
    }
}
