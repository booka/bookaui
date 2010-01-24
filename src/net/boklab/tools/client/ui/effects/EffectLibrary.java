package net.boklab.tools.client.ui.effects;

import com.google.gwt.user.client.Element;

public interface EffectLibrary {

    void fadeIn(Element element);

    void fadeOut(Element e);

    void hide(Element element);

    void hover(Element e, String cssClass);

    void hoverColors(Element element, String hover, String out);

    void slideDown(Element e);

    void slideUp(Element e);

}
