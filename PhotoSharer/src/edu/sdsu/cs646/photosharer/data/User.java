package edu.sdsu.cs646.photosharer.data;

/**
 * A class depicting a user on the photo server
 */
public class User implements Comparable<User> {

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

    public User(String id, String name) {
	this.id = id;
	this.name = name;
    }

    @Override
    public int compareTo(User other) {
	return this.name.compareTo(other.getName());
    }

    @Override
    public String toString() {
	return this.id + ":" + this.name;
    }
}
