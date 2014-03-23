package edu.sdsu.cs646.photosharer.data;

import android.os.Parcel;

/**
 * A class depicting a user-photo on the photo server
 */
public class UserPhoto extends NetworkData {

    private final String id;
    private final String name;
    private final String url;

    /**
     * @return the id
     */
    public String getId() {
	return id;
    }

    /**
     * @return the id
     */
    public String getLink() {
	return url;
    }

    /**
     * @return the name
     */
    @Override
    public String getName() {
	return name;
    }

    public UserPhoto(String id, String name, String url) {
	this.id = id;
	this.name = name;
	this.url = url;
    }

    public UserPhoto(Parcel in) {
	this.id = in.readString();
	this.name = in.readString();
	this.url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
	dest.writeString(this.id);
	dest.writeString(this.name);
	dest.writeString(this.url);
    }
}
