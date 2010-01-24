package net.boklab.tools.client.dispatcher;

/**
 * ExecutionContext instances are passed to {@link ActionHandler}s, and allows
 * them to execute sub-actions. These actions can be automatically rolled back
 * if any part of the action handler fails.
 * 
 * @author David Peterson
 */
public interface ExecutionContext extends Dispatcher {

}
