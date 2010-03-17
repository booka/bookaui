package net.boklab.module.forum.client.browser.action;

import net.boklab.core.client.ui.action.AbstractAction;
import net.boklab.core.client.ui.icons.BokIcon;
import net.boklab.core.client.ui.icons.Icons;
import net.boklab.core.client.user.UserSessionManager;
import net.boklab.module.forum.client.I18nForum;
import net.boklab.module.forum.client.browser.ForumBrowserAction;
import net.boklab.module.forum.client.browser.ForumBrowserPresenter;
import net.boklab.module.forum.client.browser.TopicPresenter;

public class AddTopicAction extends AbstractAction implements ForumBrowserAction {

    public AddTopicAction(final UserSessionManager sessions) {
	super(I18nForum.t.actionAddTopic(), Icons.get(BokIcon.add), sessions);
    }

    @Override
    public void execute() {

    }

    @Override
    public void setPresenter(final ForumBrowserPresenter indiceBrowserPresenter) {
    }

    @Override
    public void setSelected(final TopicPresenter selected) {
    }

}
