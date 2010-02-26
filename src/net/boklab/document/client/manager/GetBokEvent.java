package net.boklab.document.client.manager;

import com.google.gwt.event.shared.GwtEvent;

public class GetBokEvent extends GwtEvent<GetBokHandler> {

    public static final Type<GetBokHandler> TYPE = new Type<GetBokHandler>();
    private final String bokId;
    private final String bokType;

    public GetBokEvent(final String bokId, final String bokType) {
	this.bokId = bokId;
	this.bokType = bokType;
    }

    @Override
    public Type<GetBokHandler> getAssociatedType() {
	return TYPE;
    }

    public String getBokId() {
	return bokId;
    }

    public boolean isBokType(final String type) {
	return type == null ? bokType == null : type.equals(bokType);
    }

    @Override
    protected void dispatch(final GetBokHandler handler) {
	handler.onGetBok(this);
    }

}
