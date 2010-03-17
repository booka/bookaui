package net.boklab.module.archives.client.archive.browser;

import net.boklab.core.client.ui.browser.AbstractBrowserAction;
import net.boklab.core.client.user.UserSessionManager;

public abstract class ArchiveBrowserAction extends
	AbstractBrowserAction<ArchiveBrowserPresenter, ArchiveItemPresenter> {

    public ArchiveBrowserAction(final String name, final String iconStyle,
	    final UserSessionManager sessions) {
	super(name, iconStyle, sessions);
    }

}
