package net.boklab.document.client.bok.action;

import net.boklab.document.client.bok.BokPresenter;

public abstract class BokAction {

    private final String type;
    private final String label;

    public BokAction(final String type, final String label) {
	this.type = type;
	this.label = label;
    }

    public abstract void execute(BokPresenter presenter);

    public String getLabel() {
	return label;
    }

    public String getType() {
	return type;
    }

    public abstract boolean isApplicable(BokPresenter presenter);

}
