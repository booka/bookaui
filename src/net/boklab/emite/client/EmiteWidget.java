package net.boklab.emite.client;

import com.calclab.hablar.basic.client.ui.HablarWidget;
import com.calclab.hablar.basic.client.ui.pages.PagesWidget;
import com.calclab.hablar.basic.client.ui.pages.panel.AccordionPages;
import com.calclab.hablar.chat.client.HablarChat;
import com.calclab.hablar.roster.client.HablarRoster;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class EmiteWidget extends Composite implements EmiteDisplay {

    @Inject
    public EmiteWidget() {
	HablarWidget widget = new HablarWidget(new PagesWidget(new AccordionPages()));
	HablarChat.install(widget);
	HablarRoster.install(widget, false);
	initWidget(widget);
	widget.setSize("100%", "100%");
    }

    @Override
    public Widget asWidget() {
	return this;
    }

}
