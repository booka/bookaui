package net.boklab.module.forum.client.browser;

import net.boklab.tools.client.mvp.Presenter;

public class TopicPresenter implements Presenter<TopicDisplay> {

    private final TopicDisplay display;

    public TopicPresenter(final TopicDisplay display) {
	this.display = display;
    }

    @Override
    public void bind() {
    }

    @Override
    public TopicDisplay getDisplay() {
	return display;
    }

}
