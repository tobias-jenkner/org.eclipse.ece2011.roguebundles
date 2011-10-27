package org.eclipse.ece2011.roguebundles.bundlejail.resolverhook;

import java.util.Collection;

import org.osgi.framework.hooks.resolver.ResolverHook;
import org.osgi.framework.hooks.resolver.ResolverHookFactory;
import org.osgi.framework.wiring.BundleRevision;

public class BundleJailResolverHookFactory implements ResolverHookFactory {

	@Override
	public ResolverHook begin(Collection<BundleRevision> triggers) {
		return new BundleJailResolverHook();
	}

}
