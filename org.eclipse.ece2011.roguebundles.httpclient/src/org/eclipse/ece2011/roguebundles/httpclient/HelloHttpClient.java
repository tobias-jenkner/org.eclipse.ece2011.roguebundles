package org.eclipse.ece2011.roguebundles.httpclient;

import org.apache.commons.httpclient.params.DefaultHttpParams;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.params.HttpParams;

public class HelloHttpClient {

	public static void hello() {
		HttpParams defaultParams = DefaultHttpParams.getDefaultParams();
		int connectionTimeout = defaultParams.getIntParameter(
				HttpConnectionParams.CONNECTION_TIMEOUT, 0);
		System.err.println("Default http connection timeout is : "
				+ connectionTimeout);
	}

}
