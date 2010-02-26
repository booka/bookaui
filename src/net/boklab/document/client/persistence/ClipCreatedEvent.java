package net.boklab.document.client.persistence;

import net.boklab.core.client.model.BokResponse;
import net.boklab.core.client.persistence.BokResponseEvent;
import net.boklab.document.client.model.Clip;

public class ClipCreatedEvent extends BokResponseEvent<ClipCreatedHandler> {

    private static final Type<ClipCreatedHandler> TYPE = new Type<ClipCreatedHandler>();

    public static Type<ClipCreatedHandler> getType() {
	return TYPE;
    }

    private final Clip clip;

    public ClipCreatedEvent(final Clip clip, final BokResponse response) {
	super(clip, response);
	this.clip = clip;
    }

    @Override
    public Type<ClipCreatedHandler> getAssociatedType() {
	return getType();
    }

    public Clip getClip() {
	return clip;
    }

    @Override
    protected void dispatch(final ClipCreatedHandler handler) {
	handler.onClipCreated(this);
    }

}
