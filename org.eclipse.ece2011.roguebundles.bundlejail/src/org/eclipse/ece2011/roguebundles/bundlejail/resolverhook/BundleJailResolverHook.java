package org.eclipse.ece2011.roguebundles.bundlejail.resolverhook;

import java.util.Collection;

import org.eclipse.ece2011.roguebundles.bundlejail.BundleJailCellFilter;
import org.osgi.framework.Bundle;
import org.osgi.framework.hooks.resolver.ResolverHook;
import org.osgi.framework.wiring.BundleCapability;
import org.osgi.framework.wiring.BundleRequirement;
import org.osgi.framework.wiring.BundleRevision;

/**
 * {@link ResolverHook} based on the {@link BundleJailCellFilter}
 * 
 * @author tobias.jenkner
 */
public class BundleJailResolverHook implements ResolverHook {

	private static class BundleCapabilityBundleJailCellFilter extends
			BundleJailCellFilter<BundleCapability> {

		public BundleCapabilityBundleJailCellFilter(Bundle initiator,
				Collection<BundleCapability> candidates) {
			super(initiator, candidates);
		}

		@Override
		protected Bundle adaptToBundle(BundleCapability bundleCapability) {
			return bundleCapability.getRevision().getBundle();
		}
	}

	@Override
	public void filterResolvable(Collection<BundleRevision> candidates) {
		// nothing to do here for bundle jails
	}

	@Override
	public void filterSingletonCollisions(BundleCapability singleton,
			Collection<BundleCapability> collisionCandidates) {
		System.err.println("BundleJailResolverHook.filterSingletonCollisions");
		BundleJailCellFilter<BundleCapability> filter = new BundleCapabilityBundleJailCellFilter(
				singleton.getRevision().getBundle(), collisionCandidates);
		filter.removeNonMatching();
	}

	@Override
	public void filterMatches(BundleRequirement requirement,
			Collection<BundleCapability> candidates) {
		BundleJailCellFilter<BundleCapability> filter = new BundleCapabilityBundleJailCellFilter(
				requirement.getRevision().getBundle(), candidates);
		filter.removeNonMatchingAndDefaultAlternatives();
	}

	@Override
	public void end() {
		System.err.println("BundleJailResolverHook.end");
	}

}
