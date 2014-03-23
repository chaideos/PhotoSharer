package edu.sdsu.cs646.photosharer.data;

import android.os.Parcelable;

/**
 * An abstract class depicting the Network Data to be used inside the
 * PhotoSharer application.
 */
public abstract class NetworkData implements Comparable<NetworkData>,
	Parcelable {

    @Override
    public int describeContents() {
	// TODO Auto-generated method stub
	return 0;
    }

    abstract public String getName();

    @Override
    public int compareTo(NetworkData other) {
	return this.getName().compareTo(other.getName());
    }
}
