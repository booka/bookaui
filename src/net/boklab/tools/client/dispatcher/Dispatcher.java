package net.boklab.tools.client.dispatcher;

public interface Dispatcher {
    /**
     * Executes the specified action and returns the appropriate result.
     * 
     * @param <T>
     *            The {@link Result} type.
     * @param action
     *            The {@link Action}.
     * @return The action's result.
     * @throws ActionException
     *             if the action execution failed.
     */
    <A extends Action, C extends ActionCallback<R>, R extends Object> void execute(A action, C callback);

}
