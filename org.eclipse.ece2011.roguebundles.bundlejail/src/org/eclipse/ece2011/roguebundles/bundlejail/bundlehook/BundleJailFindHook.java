package org.eclipse.ece2011.roguebundles.bundlejail.bundlehook;

import java.util.Collection;

import org.eclipse.ece2011.roguebundles.bundlejail.BundleJailCellFilter;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.hooks.bundle.FindHook;

/**
 * Isolation for {@link Bundle} lookup based on the {@link BundleJailCellFilter}
 * 
 * @author tobias.jenkner
 */
public class BundleJailFindHook implements FindHook {

	@Override
	public void find(BundleContext context, Collection<Bundle> bundles) {
		BundleJailCellFilter<Bundle> filter = new BundleJailCellFilter<Bundle>(
				context.getBundle(), bundles) {

			@Override
			protected Bundle adaptToBundle(Bundle t) {
				return t;
			}
		};
		filter.removeNonMatchingAndDefaultAlternatives();

	}
}
