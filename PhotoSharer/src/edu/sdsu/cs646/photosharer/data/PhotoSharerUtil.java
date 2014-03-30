package edu.sdsu.cs646.photosharer.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * A utility class which contains methods used various classes in the
 * application. The class would be a singleton. Follows the singleton pattern as
 * discussed by Joshua Block in Effective Java
 * [http://stackoverflow.com/questions
 * /70689/what-is-an-efficient-way-to-implement-a-singleton-pattern-in-java]
 */
public enum PhotoSharerUtil {

    INSTANCE;

    /**
     * @param data
     *            - A list of Network Data
     * @return - A list with network data separated by its starting letters.
     */
    public List<Object> getDataWithHeaders(List<User> data) {
	List<Object> headerData = new ArrayList<Object>();
	if (data != null && !data.isEmpty()) {
	    Collections.sort(data);
	    headerData.add(String.valueOf(data.get(0).getName().charAt(0)));
	    headerData.add(data.get(0));
	    for (int i = 0, j = i + 1; j < data.size() && i < data.size(); j++) {
		String firstHeader = String.valueOf(
			data.get(i).getName().charAt(0)).toUpperCase(Locale.US);
		String secondHeader = String.valueOf(
			data.get(j).getName().charAt(0)).toUpperCase(Locale.US);
		if (!firstHeader.equals(secondHeader)) {
		    headerData.add(secondHeader);
		    i = j;
		}
		headerData.add(data.get(j));
	    }
	}
	return headerData;
    }
}
