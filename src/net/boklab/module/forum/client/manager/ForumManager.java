package net.boklab.module.forum.client.manager;

import net.boklab.core.client.model.Bok;
import net.boklab.core.client.persistence.AbstractBokManager;
import net.boklab.core.client.persistence.ManagerMessages;
import net.boklab.module.forum.client.I18nForum;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.workspace.client.msg.MessageManager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ForumManager extends AbstractBokManager {

    @Inject
    public ForumManager(final EventBus eventBus, final MessageManager messages) {
	super(Bok.FORUM, eventBus, messages, new ManagerMessages() {
	    @Override
	    public String open() {
		return I18nForum.t.openForumUntitled();
	    }

	    @Override
	    public String open(final String title) {
		return I18nForum.t.openForum(title);
	    }

	    @Override
	    public String update(final String title) {
		return I18nForum.t.update();
	    }
	});
    }

}
