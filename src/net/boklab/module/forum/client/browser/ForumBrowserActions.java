package net.boklab.module.forum.client.browser;

import net.boklab.core.client.ui.browser.BrowserActions;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ForumBrowserActions extends BrowserActions<ForumBrowserPresenter, TopicPresenter> {

    @Inject
    public ForumBrowserActions() {
    }
}
