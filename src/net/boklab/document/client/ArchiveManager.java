package net.boklab.document.client;

import net.boklab.core.client.model.Bok;
import net.boklab.core.client.persistence.AbstractBokManager;
import net.boklab.core.client.persistence.ManagerMessages;
import net.boklab.tools.client.eventbus.EventBus;
import net.boklab.workspace.client.msg.MessageManager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ArchiveManager extends AbstractBokManager {
    @Inject
    public ArchiveManager(final EventBus eventBus, final MessageManager messages) {
	super(Bok.ARCHIVE, eventBus, messages, new ManagerMessages() {
	    @Override
	    public String open() {
		return I18nDocs.t.openArchiveUntitled();
	    }

	    @Override
	    public String open(final String title) {
		return I18nDocs.t.openArchive(title);
	    }

	    @Override
	    public String update(final String title) {
		return I18nDocs.t.update();
	    }
	});
    }

    public void openArchivesOfProject(final Bok project) {
	final Bok archives = project.getFirstChild(Bok.ARCHIVE);
	assert archives != null : "Project achives children bok not found (AbstractBokManager)";
	open(project.getId(), project.getTitle(), false);
    }
}