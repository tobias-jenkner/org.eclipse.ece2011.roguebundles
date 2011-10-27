package org.eclipse.ece2011.roguebundles.bundlejail;

import java.util.Collection;
import java.util.HashSet;
import java.util.logging.Logger;

import org.osgi.framework.Bundle;

public abstract class BundleJailCellFilter<T> {

	private static final Logger logger = Logger
			.getLogger(BundleJailCellFilter.class.getName());

	private final HashSet<T> exactMatches = new HashSet<T>();

	private final HashSet<T> defaultMatches = new HashSet<T>();

	private final HashSet<T> noMatches = new HashSet<T>();

	private final Collection<T> candidates;

	private final BundleJailCell bundleCompartment;

	private final Bundle initiator;

	public BundleJailCellFilter(Bundle initiator, Collection<T> candidates) {
		this.initiator = initiator;
		this.bundleCompartment = new BundleJailCell(initiator.getLocation());
		this.candidates = candidates;
		filterCandidates();
	}

	private void filterCandidates() {
		for (T bundleReference : candidates) {
			Bundle bundle = adaptToBundle(bundleReference);
			BundleJailCell toCheck = new BundleJailCell(
					bundle.getLocation());
			if (bundleCompartment.matches(toCheck)) {
				logger.info("exact match found for requiring bundle '"
						+ initiator.getSymbolicName() + "' in compartment '"
						+ bundleCompartment + "': " + bundle.getSymbolicName());
				exactMatches.add(bundleReference);
			} else if (toCheck.isDefault()) {
				logger.info("default match found for requiring bundle '"
						+ initiator.getSymbolicName() + "' in compartment '"
						+ bundleCompartment + "': " + bundle.getSymbolicName());
				defaultMatches.add(bundleReference);
			} else
				noMatches.add(bundleReference);
		}
	}

	protected abstract Bundle adaptToBundle(T t);

	/**
	 * Remove all capabilities that come from a different non default
	 * compartment, and remove capabilities from the default compartment if we
	 * have a match from our own compartment.
	 */
	public void removeNonMatchingAndDefaultAlternatives() {
		if (exactMatches.size() > 0) {
			candidates.removeAll(defaultMatches);
		}
		removeNonMatching();
	}

	/**
	 * Remove all non matching capabilities.
	 */
	public void removeNonMatching() {
		candidates.removeAll(noMatches);
	}
}
