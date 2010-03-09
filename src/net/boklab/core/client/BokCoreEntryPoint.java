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
	Icons.set(BokIcon.none, css.none());
	Icons.set(BokIcon.unlock, css.unlockIcon());
    }
}
