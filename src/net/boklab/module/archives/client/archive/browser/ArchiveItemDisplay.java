package net.boklab.module.archives.client.archive.browser;

import net.boklab.core.client.ui.browser.BrowserItemDisplay;

import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasText;

public interface ArchiveItemDisplay extends BrowserItemDisplay {

    HasHTML getDescription();

    HasText getDocumentTitle();

    HasHTML getExtra();

}
