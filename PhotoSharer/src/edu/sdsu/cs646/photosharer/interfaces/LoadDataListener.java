package edu.sdsu.cs646.photosharer.interfaces;

import java.util.List;

import edu.sdsu.cs646.photosharer.data.User;

/**
 * A callback interface to be used with an AsyncTask and contains callback
 * methods to handle data completion.
 */
public interface LoadDataListener {

    public void onDataLoadComplete(List<User> data);

    public void preDataFetch();
}
