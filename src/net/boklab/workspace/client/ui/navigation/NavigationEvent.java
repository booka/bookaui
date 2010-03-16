package net.boklab.workspace.client.ui.navigation;

import com.google.gwt.event.shared.GwtEvent;

public class NavigationEvent extends GwtEvent<NavigationHandler> {

    private static final Type<NavigationHandler> TYPE = new Type<NavigationHandler>();

    public static Type<NavigationHandler> getType() {
	return TYPE;
    }

    private final String name;

    public NavigationEvent(final String name) {
	this.name = name;
    }

    @Override
    public Type<NavigationHandler> getAssociatedType() {
	return getType();
    }

    public String getName() {
	return name;
    }

    public boolean isNavigation(final String name) {
	return this.name.equals(name);
    }

    @Override
    public String toDebugString() {
	return super.toDebugString() + name;
    }

    @Override
    protected void dispatch(final NavigationHandler handler) {
	handler.onNavigation(this);
    }

}
