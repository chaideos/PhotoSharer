package edu.sdsu.cs646.photosharer.interfaces;

import java.util.List;

import edu.sdsu.cs646.photosharer.data.NetworkData;

/**
 * A callback interface to be used with an AsyncTask and contains callback
 * methods to handle data completion.
 */
public interface LoadDataListener<T extends NetworkData> {

    public void onDataLoadComplete(List<T> data);

    public void preDataFetch();
}
