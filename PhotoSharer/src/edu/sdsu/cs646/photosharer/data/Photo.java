package edu.sdsu.cs646.photosharer.data;

/**
 * A class depicting a user on the photo server
 */
public class Photo implements Comparable<Photo> {

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
    public String getName() {
	return name;
    }

    public Photo(String id, String name) {
	this.id = id;
	this.name = name;
    }

    @Override
    public int compareTo(Photo other) {
	return getName().compareTo(other.getName());
    }
}
