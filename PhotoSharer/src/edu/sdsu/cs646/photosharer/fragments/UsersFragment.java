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

import java.util.List;

import android.app.ListFragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import edu.sdsu.cs646.photosharer.R;
import edu.sdsu.cs646.photosharer.asynctasks.AddUsersTask;
import edu.sdsu.cs646.photosharer.asynctasks.RetrieveUsersTask;
import edu.sdsu.cs646.photosharer.databases.DatabaseHelper;
import edu.sdsu.cs646.photosharer.interfaces.LoadDataListener;
import edu.sdsu.cs646.photosharer.uiadapters.UsersListAdapter;

/**
 * A ListFragment depicting the users list
 */
public class UsersFragment extends ListFragment implements LoadDataListener {

    /**
     * A reference to the database helper class used to perform db operations.
     */
    DatabaseHelper dbHelper;

    /**
     * Indicates the number of users in the database.
     */
    long numOfUsers;

    /**
     * Indicates the base URL of the photo server.
     */
    String baseUrl;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	    Bundle savedInstanceState) {
	return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
	super.onActivityCreated(savedInstanceState);
	dbHelper = new DatabaseHelper(getActivity());
	numOfUsers = dbHelper.numOfUsers();
	if (numOfUsers <= 0) {
	    new RetrieveUsersTask(this).execute(baseUrl);
	} else {
	    setAdapter(dbHelper.getUsers());
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
    public void onDataLoadComplete(List<String> data) {
	if (getActivity() != null) {
	    setAdapter(data);
	    progressDialog.dismiss();
	    String[] userArray = new String[data.size()];
	    new AddUsersTask(getActivity()).execute(data.toArray(userArray));
	}
    }

    @Override
    public void preDataFetch() {
	if (getActivity() != null) {
	    progressDialog = new ProgressDialog(getActivity());
	    progressDialog.setTitle(getResources().getString(
		    R.string.retrieving_users_str));
	    progressDialog.setIndeterminate(true);
	    progressDialog.show();
	}
    }

    private void setAdapter(List<String> data) {
	if (getActivity() != null) {
	    ListAdapter adapter = new UsersListAdapter(getActivity(), data);
	    setListAdapter(adapter);
	}
    }
}
