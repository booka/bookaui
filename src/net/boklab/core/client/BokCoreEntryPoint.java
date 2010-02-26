package net.boklab.core.client;

import net.boklab.core.client.icons.Icons;
import net.boklab.core.client.icons.res.BokIcon;
import net.boklab.core.client.icons.res.SharedCssIcons;
import net.boklab.core.client.icons.res.SharedIcons;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class BokCoreEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
	final SharedCssIcons css = new SharedIcons().style;
	Icons.set(BokIcon.loading, css.loadingIcon());
	final BokCoreMessages messages = GWT.create(BokCoreMessages.class);
	I18nBok.set(messages);
    }
}
