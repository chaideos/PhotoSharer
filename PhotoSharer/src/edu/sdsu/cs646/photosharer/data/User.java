package edu.sdsu.cs646.photosharer.data;

import android.os.Parcel;

/**
 * A class depicting a user on the photo server
 */
public class User extends NetworkData {

    private final String id;
    private final String name;

    /**
     * @return the id
     */
    public String getId() {
	return id;
    }

    /**
     * @return the name
     */
    @Override
    public String getName() {
	return name;
    }

    public User(Parcel in) {
	this.id = in.readString();
	this.name = in.readString();
    }

    public User(String id, String name) {
	this.id = id;
	this.name = name;
    }

    @Override
    public String toString() {
	return this.id + ":" + this.name;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
	dest.writeString(this.id);
	dest.writeString(this.name);
    }
}
