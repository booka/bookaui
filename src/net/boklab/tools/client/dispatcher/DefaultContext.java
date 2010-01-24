package net.boklab.tools.client.dispatcher;

public class DefaultContext implements ExecutionContext {
    private final Dispatcher dispatcher;

    public DefaultContext(Dispatcher dispatcher) {
	this.dispatcher = dispatcher;
    }

    @Override
    public <A extends Action, C extends ActionCallback<R>, R> void execute(A action, C callback) {
	dispatcher.execute(action, callback);
    }

}
