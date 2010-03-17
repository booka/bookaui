package net.boklab.core.client;

import net.boklab.core.client.ui.icons.BokIcon;
import net.boklab.core.client.ui.icons.Icons;
import net.boklab.core.client.ui.icons.SharedCssIcons;
import net.boklab.core.client.ui.icons.SharedIcons;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class BokCoreEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
	final SharedCssIcons css = new SharedIcons().style;
	final BokCoreMessages messages = GWT.create(BokCoreMessages.class);
	I18nCore.t = messages;

	Icons.set(BokIcon.loading, css.loadingIcon());
	Icons.set(BokIcon.add, css.addIcon());
	Icons.set(BokIcon.delete, css.none());
	Icons.set(BokIcon.lock, css.lockIcon());
	Icons.set(BokIcon.unlock, css.unlockIcon());
	Icons.set(BokIcon.edit, css.editIcon());
	Icons.set(BokIcon.search, css.searchIcon());
	Icons.set(BokIcon.tag, css.tagIcon());
	Icons.set(BokIcon.menu, css.menuIcon());
    }
}
