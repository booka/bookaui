package net.boklab.core.client.persistence;

import net.boklab.core.client.model.BokQuery;

import com.google.gwt.event.shared.GwtEvent;

public class RetrieveBokListEvent extends GwtEvent<RetrieveBokListHander> {
    private static final Type<RetrieveBokListHander> TYPE = new Type<RetrieveBokListHander>();

    public static Type<RetrieveBokListHander> getType() {
	return TYPE;
    }
    private final BokQuery query;

    private final BokListRetrievedHandler handler;

    public RetrieveBokListEvent(final BokQuery query, final BokListRetrievedHandler handler) {
	this.query = query;
	this.handler = handler;
    }

    @Override
    public Type<RetrieveBokListHander> getAssociatedType() {
	return TYPE;
    }

    public BokListRetrievedHandler getHandler() {
	return handler;
    }

    public BokQuery getQuery() {
	return query;
    }

    @Override
    public String toDebugString() {
	return super.toDebugString() + query.toParams();
    }

    @Override
    protected void dispatch(final RetrieveBokListHander handler) {
	handler.onRetrieveBokList(this);
    }

}
