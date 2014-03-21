package edu.sdsu.cs646.photosharer.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A utility class which contains methods used various classes in the
 * application. The class would be a singleton. Follows the singleton pattern as
 * discussed by Joshua Block in Effective Java
 * [http://stackoverflow.com/questions
 * /70689/what-is-an-efficient-way-to-implement-a-singleton-pattern-in-java]
 */
public enum PhotoSharerUtil {

    INSTANCE;

    public List<String> getHeaders(List<String> data) {
	List<String> headers = new ArrayList<String>();
	if (data != null && !data.isEmpty()) {
	    Collections.sort(data);
	    headers.add(String.valueOf(data.get(0).charAt(0)));

	    for (int i = 0, j = i + 1; j < data.size() && i < data.size(); j++) {
		if (data.get(i).charAt(0) != data.get(j).charAt(0)) {
		    headers.add(String.valueOf(data.get(j).charAt(0)));
		    i = j;
		}
	    }
	}
	return headers;
    }
}
