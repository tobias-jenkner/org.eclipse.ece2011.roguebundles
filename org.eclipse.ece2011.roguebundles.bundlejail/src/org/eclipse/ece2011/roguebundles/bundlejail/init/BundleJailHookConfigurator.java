package org.eclipse.ece2011.roguebundles.bundlejail.init;

import org.eclipse.osgi.baseadaptor.HookConfigurator;
import org.eclipse.osgi.baseadaptor.HookRegistry;

/**
 * This class registers the initialization code for bundlejail isolation as
 * {@link AdapterHook}
 * 
 * @author tobias.jenkner
 */
public class BundleJailHookConfigurator implements HookConfigurator {

	@Override
	public void addHooks(HookRegistry hookRegistry) {
		hookRegistry.addAdaptorHook(new BundleJailAdapterHook());
	}

}
