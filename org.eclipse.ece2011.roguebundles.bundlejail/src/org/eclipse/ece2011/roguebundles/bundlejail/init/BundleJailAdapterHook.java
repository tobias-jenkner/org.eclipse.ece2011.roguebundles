package org.eclipse.ece2011.roguebundles.bundlejail.init;

import java.io.IOException;
import java.net.URLConnection;
import java.util.Hashtable;
import java.util.Properties;

import org.eclipse.ece2011.roguebundles.bundlejail.bundlehook.BundleJailEventHook;
import org.eclipse.ece2011.roguebundles.bundlejail.bundlehook.BundleJailFindHook;
import org.eclipse.ece2011.roguebundles.bundlejail.resolverhook.BundleJailResolverHookFactory;
import org.eclipse.ece2011.roguebundles.bundlejail.servicehook.BundleJailServiceEventListenerHook;
import org.eclipse.ece2011.roguebundles.bundlejail.servicehook.BundleJailServiceFindHook;
import org.eclipse.ece2011.roguebundles.bundlejail.urlhandler.FileDistinctionURLStreamHandlerService;
import org.eclipse.osgi.baseadaptor.BaseAdaptor;
import org.eclipse.osgi.baseadaptor.hooks.AdaptorHook;
import org.eclipse.osgi.framework.log.FrameworkLog;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.ServiceRegistration;
import org.osgi.framework.hooks.resolver.ResolverHookFactory;
import org.osgi.service.url.URLConstants;
import org.osgi.service.url.URLStreamHandlerService;

public class BundleJailAdapterHook implements AdaptorHook {

	public static final String PROTOCOL = "filedistinction";

	private ServiceRegistration<ResolverHookFactory> resolverHookFactoryServiceRegistration;

	private ServiceRegistration<BundleJailFindHook> bundleFindHookServiceRegistration;

	private ServiceRegistration<BundleJailServiceFindHook> serviceFindHookServiceRegistration;

	private ServiceRegistration<BundleJailEventHook> bundleEventHookServiceRegistration;

	private ServiceRegistration<?> urlStreamHandlerServiceRegistration;

	private ServiceRegistration<BundleJailServiceEventListenerHook> serviceEventListenerHookServiceRegistration;

	@Override
	public void initialize(BaseAdaptor adaptor) {
		// nothing to initialize here

	}

	@Override
	public void frameworkStart(BundleContext context) throws BundleException {
		resolverHookFactoryServiceRegistration = context.registerService(
				ResolverHookFactory.class, new BundleJailResolverHookFactory(), null);
		bundleFindHookServiceRegistration = context.registerService(
				BundleJailFindHook.class,
				new BundleJailFindHook(), null);
		bundleEventHookServiceRegistration = context.registerService(
				BundleJailEventHook.class,
				new BundleJailEventHook(), null);
		serviceFindHookServiceRegistration = context.registerService(
				BundleJailServiceFindHook.class,
				new BundleJailServiceFindHook(), null);
		serviceEventListenerHookServiceRegistration = context.registerService(
				BundleJailServiceEventListenerHook.class,
				new BundleJailServiceEventListenerHook(), null);
		registerURLStreamHandlerService(context);
	}

	private void registerURLStreamHandlerService(BundleContext context) {
		Hashtable<String, String[]> properties = new Hashtable<String, String[]>(
				1);
		properties.put(URLConstants.URL_HANDLER_PROTOCOL,
				new String[] { PROTOCOL });
		urlStreamHandlerServiceRegistration = context.registerService(
				URLStreamHandlerService.class.getName(),
				new FileDistinctionURLStreamHandlerService(), properties);
	}

	@Override
	public void frameworkStop(BundleContext context) throws BundleException {
		// nothing to do here
	}

	@Override
	public void frameworkStopping(BundleContext context) {
		urlStreamHandlerServiceRegistration.unregister();
		serviceEventListenerHookServiceRegistration.unregister();
		serviceFindHookServiceRegistration.unregister();
		bundleEventHookServiceRegistration.unregister();
		bundleFindHookServiceRegistration.unregister();
		resolverHookFactoryServiceRegistration.unregister();
	}

	@Override
	public void addProperties(Properties properties) {
		// we do not add properties

	}

	@Override
	public URLConnection mapLocationToURLConnection(String location)
			throws IOException {
		// we do not take part in this game
		return null;
	}

	@Override
	public void handleRuntimeError(Throwable error) {
		// we do not handle runtime errors

	}

	@Override
	public FrameworkLog createFrameworkLog() {
		// the framework log getts created by another adapter hook
		return null;
	}

}
