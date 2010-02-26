package net.boklab.emite.client;

import com.calclab.hablar.chat.client.HablarChat;
import com.calclab.hablar.core.client.HablarWidget;
import com.calclab.hablar.core.client.HablarDisplay.Layout;
import com.calclab.hablar.roster.client.HablarRoster;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class EmiteWidget extends Composite implements EmiteDisplay {

    @Inject
    public EmiteWidget() {
	final HablarWidget widget = new HablarWidget(Layout.accordion);
	HablarChat.install(widget);
	HablarRoster.install(widget);
	initWidget(widget);
	widget.setSize("100%", "100%");
    }

    @Override
    public Widget asWidget() {
	return this;
    }

}
