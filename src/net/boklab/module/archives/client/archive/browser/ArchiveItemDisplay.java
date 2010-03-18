package net.boklab.module.archives.client.archive.browser;

import net.boklab.core.client.ui.browser.BrowserItemDisplay;

import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasText;

public interface ArchiveItemDisplay extends BrowserItemDisplay {

    void addStyleName(String style);

    HasHTML getDescription();

    HasText getDocumentTitle();

    HasHTML getExtra();

    void removeStyleName(String style);

}
