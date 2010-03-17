package net.boklab.module.forum.client.browser;

import net.boklab.core.client.ui.browser.BrowserItemPresenter;

public class TopicPresenter extends BrowserItemPresenter<TopicDisplay> {

    public TopicPresenter(final ForumBrowserPresenter parent, final TopicDisplay display) {
	super(display);
    }

    @Override
    public void bind() {
    }

    @Override
    public TopicDisplay getDisplay() {
	return display;
    }

    @Override
    public void setSelected(final boolean selected) {

    }

}
