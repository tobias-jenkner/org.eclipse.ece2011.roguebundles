package org.eclipse.ece2011.roguebundles.application;

/**
 * Interface defining the application's command IDs.
 * Key bindings can be defined for specific commands.
 * To associate an action with a command, use IAction.setActionDefinitionId(commandId).
 *
 * @see org.eclipse.jface.action.IAction#setActionDefinitionId(String)
 */
public interface ICommandIds {

    public static final String CMD_OPEN = "org.eclipse.ece2011.roguebundles.application.open";
    public static final String CMD_OPEN_MESSAGE = "org.eclipse.ece2011.roguebundles.application.openMessage";
    
}
