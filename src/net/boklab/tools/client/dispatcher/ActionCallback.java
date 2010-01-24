package net.boklab.tools.client.dispatcher;

public interface ActionCallback<R extends Object> {
    void onSuccess(R result);
}
