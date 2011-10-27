package org.eclipse.ece2011.roguebundles.bundlejail.bundlehook;

import java.util.Collection;

import org.eclipse.ece2011.roguebundles.bundlejail.BundleJailCellFilter;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleEvent;
import org.osgi.framework.hooks.bundle.EventHook;

public class BundleJailEventHook implements EventHook {

	@Override
	public void event(BundleEvent event, Collection<BundleContext> contexts) {
		BundleJailCellFilter<BundleContext> filter = new BundleJailCellFilter<BundleContext>(
				event.getBundle(), contexts) {
			@Override
			protected Bundle adaptToBundle(BundleContext t) {
				return t.getBundle();
			}
		};
		filter.removeNonMatching();

	}

}
