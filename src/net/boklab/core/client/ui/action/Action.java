package net.boklab.core.client.ui.action;


public interface Action {

    void execute();

    String getIconStyle();

    String getName();

    boolean isVisible();

    void setDisplay(ActionDisplay display);

}
