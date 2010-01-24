package net.boklab.tools.client.ui.effects;

import net.boklab.tools.client.mvp.Display;

import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Widget;

public class Effects {

    static EffectLibrary library = new JqueryEffects();

    public static void fadeIn(Display display) {
	library.fadeIn(e(display));
    }

    public static void fadeOut(Display display) {
	library.fadeOut(e(display));
    }

    public static void fadeOut(Widget widget) {
	library.fadeOut(e(widget));
    }

    public static void hide(Display display) {
	library.hide(e(display));
    }

    public static void hide(Widget widget) {
	library.hide(e(widget));
    }

    public static void hover(Display display, String cssClass) {
	library.hover(e(display), cssClass);
    }

    public static void hoverColors(Display display, String hover, String out) {
	library.hoverColors(e(display), hover, out);
    }

    public static void slideDown(Display display) {
	library.slideDown(e(display));
    }

    private static Element e(Display display) {
	return (display.asWidget()).getElement();
    }

    private static Element e(Widget widget) {
	return widget.getElement();
    }
}
