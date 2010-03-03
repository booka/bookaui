package net.boklab.site.client.ui;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasHTML;

public interface ProjectDisplay extends Display, HasClickHandlers {
    HasHTML getTitleHeader();
}
