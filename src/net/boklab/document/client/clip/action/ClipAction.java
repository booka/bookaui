package net.boklab.document.client.clip.action;

import net.boklab.document.client.clip.ClipPresenter;

public abstract class ClipAction {

    private final String type;
    private final String label;

    public ClipAction(final String type, final String label) {
	this.type = type;
	this.label = label;
    }

    public abstract void execute(ClipPresenter presenter);

    public String getLabel() {
	return label;
    }

    public String getType() {
	return type;
    }

    public abstract boolean isApplicable(ClipPresenter presenter);

}
