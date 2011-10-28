package org.eclipse.ece2011.roguebundles.httpclienta;

import org.apache.commons.httpclient.params.DefaultHttpParams;
import org.apache.commons.httpclient.params.DefaultHttpParamsFactory;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.params.HttpParams;

/**
 * This Factory sets the <code>org.apache.commons.httpclient.params.HttpMethodParams.SINGLE_COOKIE_HEADER</code>
 * parameter to <code>true</code>. This is required by the siteminder webagent which can only then read the siteminder
 * session cookie.
 * 
 * @author Tobias Jenkner
 */
public class MyDefaultHttpParamsFactory extends DefaultHttpParamsFactory {

    /**
     * Registers an instance of this class as http parameter factory in the apache commons httpclient library.
     */
    public static void register() {
        DefaultHttpParams.setHttpParamsFactory(new MyDefaultHttpParamsFactory());
    }

    /**
     * Resets the http parameter factory in the apache commons httpclient library to its default.
     */
    public static void unregister() {
        DefaultHttpParams.setHttpParamsFactory(new DefaultHttpParamsFactory());
    }

    @Override
    public HttpParams getDefaultParams() {
        HttpParams params = super.getDefaultParams();
        params.setParameter(HttpConnectionParams.CONNECTION_TIMEOUT, Integer.valueOf(100));
        return params;
    }

}
