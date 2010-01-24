package net.boklab.tools.client.dispatcher;

/**
 * Instances of this interface will handle specific types of {@link Action}
 * classes.
 * 
 * @author David Peterson
 */
public interface ActionHandler<A extends Action, C extends ActionCallback<? extends Object>> {

    /**
     * Handles the specified action.
     * 
     * @param <T>
     *            The Result type.
     * @param action
     *            The action.
     * @return The {@link Result}.
     */
    void execute(A action, C callback, ExecutionContext context);

}
