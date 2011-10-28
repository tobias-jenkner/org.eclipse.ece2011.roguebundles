package org.eclipse.ece2011.roguebundles.bundlejail.servicehook;

import java.util.Collection;
import java.util.Map;

import org.eclipse.ece2011.roguebundles.bundlejail.BundleJailCellFilter;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.hooks.service.EventListenerHook;
import org.osgi.framework.hooks.service.ListenerHook.ListenerInfo;

/**
 * Isolation for {@link ServiceEvent} publishing based on the {@link BundleJailCellFilter}.
 * 
 * @author tobias.jenkner
 */
public class BundleJailServiceEventListenerHook implements EventListenerHook {

	@Override
	public void event(ServiceEvent event,
			Map<BundleContext, Collection<ListenerInfo>> listeners) {
		BundleJailCellFilter<BundleContext> compartmentFilter = new BundleJailCellFilter<BundleContext>(
				event.getServiceReference().getBundle(), listeners.keySet()) {
			@Override
			protected Bundle adaptToBundle(BundleContext t) {
				return t.getBundle();
			}
		};
		compartmentFilter.removeNonMatchingAndDefaultAlternatives();
	}

}
