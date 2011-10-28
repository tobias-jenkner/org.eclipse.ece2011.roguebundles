package org.eclipse.ece2011.roguebundles.bundlejail.urlhandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.Permission;
import java.util.List;
import java.util.Map;

/**
 * This URL connection processes URLs that are wrapped with another URL
 * containing an additional query part.
 * 
 * The query part 
 * 
 * @author tobias.jenkner
 */
public class FileDistinctionURLConnection extends URLConnection {

	private URL delegate;

	private URLConnection delegateConnection;

	protected FileDistinctionURLConnection(URL url) throws IOException {
		super(url);
		String string = url.toString();
		delegate = new URL(string.substring(url.getProtocol().length() + 1,
				string.length() - url.getQuery().length() - 1));
		delegateConnection = delegate.openConnection();
	}

	@Override
	public void connect() throws IOException {
		delegateConnection.connect();
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return delegateConnection.getInputStream();
	}

	public void addRequestProperty(String key, String value) {
		delegateConnection.addRequestProperty(key, value);
	}

	public boolean equals(Object arg0) {
		return delegateConnection.equals(arg0);
	}

	public boolean getAllowUserInteraction() {
		return delegateConnection.getAllowUserInteraction();
	}

	public int getConnectTimeout() {
		return delegateConnection.getConnectTimeout();
	}

	public Object getContent() throws IOException {
		return delegateConnection.getContent();
	}

	public Object getContent(Class[] classes) throws IOException {
		return delegateConnection.getContent(classes);
	}

	public String getContentEncoding() {
		return delegateConnection.getContentEncoding();
	}

	public int getContentLength() {
		return delegateConnection.getContentLength();
	}

	public String getContentType() {
		return delegateConnection.getContentType();
	}

	public long getDate() {
		return delegateConnection.getDate();
	}

	public boolean getDefaultUseCaches() {
		return delegateConnection.getDefaultUseCaches();
	}

	public boolean getDoInput() {
		return delegateConnection.getDoInput();
	}

	public boolean getDoOutput() {
		return delegateConnection.getDoOutput();
	}

	public long getExpiration() {
		return delegateConnection.getExpiration();
	}

	public String getHeaderField(int n) {
		return delegateConnection.getHeaderField(n);
	}

	public String getHeaderField(String name) {
		return delegateConnection.getHeaderField(name);
	}

	public long getHeaderFieldDate(String name, long Default) {
		return delegateConnection.getHeaderFieldDate(name, Default);
	}

	public int getHeaderFieldInt(String name, int Default) {
		return delegateConnection.getHeaderFieldInt(name, Default);
	}

	public String getHeaderFieldKey(int n) {
		return delegateConnection.getHeaderFieldKey(n);
	}

	public Map<String, List<String>> getHeaderFields() {
		return delegateConnection.getHeaderFields();
	}

	public long getIfModifiedSince() {
		return delegateConnection.getIfModifiedSince();
	}

	public long getLastModified() {
		return delegateConnection.getLastModified();
	}

	public OutputStream getOutputStream() throws IOException {
		return delegateConnection.getOutputStream();
	}

	public Permission getPermission() throws IOException {
		return delegateConnection.getPermission();
	}

	public int getReadTimeout() {
		return delegateConnection.getReadTimeout();
	}

	public Map<String, List<String>> getRequestProperties() {
		return delegateConnection.getRequestProperties();
	}

	public String getRequestProperty(String key) {
		return delegateConnection.getRequestProperty(key);
	}

	public URL getURL() {
		return delegateConnection.getURL();
	}

	public boolean getUseCaches() {
		return delegateConnection.getUseCaches();
	}

	public int hashCode() {
		return delegateConnection.hashCode();
	}

	public void setAllowUserInteraction(boolean allowuserinteraction) {
		delegateConnection.setAllowUserInteraction(allowuserinteraction);
	}

	public void setConnectTimeout(int timeout) {
		delegateConnection.setConnectTimeout(timeout);
	}

	public void setDefaultUseCaches(boolean defaultusecaches) {
		delegateConnection.setDefaultUseCaches(defaultusecaches);
	}

	public void setDoInput(boolean doinput) {
		delegateConnection.setDoInput(doinput);
	}

	public void setDoOutput(boolean dooutput) {
		delegateConnection.setDoOutput(dooutput);
	}

	public void setIfModifiedSince(long ifmodifiedsince) {
		delegateConnection.setIfModifiedSince(ifmodifiedsince);
	}

	public void setReadTimeout(int timeout) {
		delegateConnection.setReadTimeout(timeout);
	}

	public void setRequestProperty(String key, String value) {
		delegateConnection.setRequestProperty(key, value);
	}

	public void setUseCaches(boolean usecaches) {
		delegateConnection.setUseCaches(usecaches);
	}

	public String toString() {
		return "FileDistinctionURLConnection[" + delegateConnection.toString()
				+ "]";
	}

}
