package org.eclipse.ece2011.roguebundles.bundlejail;

import java.net.MalformedURLException;
import java.net.URL;

public class BundleJailCell {

	// private static final Logger logger = Logger
	// .getLogger(BundleCompartment.class.getName());

	private static final String DEFAULT_NAME = "default";

	private static boolean matchesDefault(String name) {
		return name.equals(DEFAULT_NAME);
	}

	private static String getCompartmentName(final String locationURL) {
		String name;
		try {
			String trimmedLocationURL;
			int indexOfFile = locationURL.indexOf("file");
			if (indexOfFile <= 0) {
				// either it start with file or does not contain file at all
				trimmedLocationURL = locationURL;
			} else
				trimmedLocationURL = locationURL.substring(indexOfFile);

			URL url = new URL(trimmedLocationURL);
			String query = url.getQuery();
			if (query != null)
				name = query;
			else
				name = DEFAULT_NAME;

		} catch (MalformedURLException e) {
			// logger.fine("Could not ")
			name = DEFAULT_NAME;
		}
		return name;
	}

	private final String name;

	public BundleJailCell(String bundleLocationURL) {
		this.name = getCompartmentName(bundleLocationURL);
	}

	public boolean matches(BundleJailCell toCheck) {
		return name.equals(toCheck.name);
	}

	public boolean isDefault() {
		return matchesDefault(name);
	}

	@Override
	public String toString() {
		return "BundleJailCell [" + name + "]";
	}
	
	public String getName() {
		return name;
	}
}
