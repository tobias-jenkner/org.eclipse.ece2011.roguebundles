package org.eclipse.ece2011.roguebundles.bundlejail.servicehook;

import java.util.Collection;

import org.eclipse.ece2011.roguebundles.bundlejail.BundleJailCellFilter;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.hooks.service.FindHook;

/**
 * Isolation for service lookup based on the {@link BundleJailCellFilter}.
 * 
 * @author tobias.jenkner
 */
public class BundleJailServiceFindHook implements FindHook {

	@Override
	public void find(BundleContext context, String name, String filter,
			boolean allServices, Collection<ServiceReference<?>> references) {
		BundleJailCellFilter<ServiceReference<?>> compartmentFilter = new BundleJailCellFilter<ServiceReference<?>>(
				context.getBundle(), references) {

			@Override
			protected Bundle adaptToBundle(ServiceReference<?> t) {
				return t.getBundle();
			}
		};
		compartmentFilter.removeNonMatchingAndDefaultAlternatives();

	}

}
