package net.boklab.core.client.ui.action;

public interface Action<T> {

    void execute(T target);

    String getIconStyle();

    String getId();

    String getName();

    boolean isApplicable(T target);

}
