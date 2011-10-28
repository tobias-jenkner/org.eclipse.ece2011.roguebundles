package org.eclipse.ece2011.roguebundles.bundlejail.urlhandler;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.osgi.service.url.AbstractURLStreamHandlerService;
import org.osgi.service.url.URLStreamHandlerService;

/**
 * {@link URLStreamHandlerService} for opening {@link FileDistinctionURLConnection}s.
 * 
 * @author tobias.jenkner
 */
public class FileDistinctionURLStreamHandlerService extends
		AbstractURLStreamHandlerService implements URLStreamHandlerService {

	@Override
	public URLConnection openConnection(URL u) throws IOException {
		return new FileDistinctionURLConnection(u);
	}

}
