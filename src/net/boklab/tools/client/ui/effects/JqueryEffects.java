package net.boklab.tools.client.ui.effects;

import com.google.gwt.user.client.Element;

public class JqueryEffects implements EffectLibrary {
    @Override
    public native void fadeIn(Element element) /*-{
        $wnd.jQuery(element).fadeIn('slow');
    }-*/;

    @Override
    public native void fadeOut(Element element) /*-{
        $wnd.jQuery(element).fadeOut('slow');
    }-*/;

    @Override
    public native void hide(Element element) /*-{
        $wnd.jQuery(element).hide();
    }-*/;

    @Override
    public native void hover(Element element, String cssClass) /*-{
        $wnd.jQuery(element).mouseover(function() { $wnd.jQuery(this).addClass(cssClass);});
        $wnd.jQuery(element).mouseout(function() { $wnd.jQuery(this).removeClass(cssClass);});
    }-*/;

    @Override
    public native void hoverColors(Element element, String hover, String out) /*-{
        $wnd.jQuery(element).mouseover(function() { $wnd.jQuery(this).css('background-color', hover);});
        $wnd.jQuery(element).mouseout(function() { $wnd.jQuery(this).css('background-color', out);});
    }-*/;

    @Override
    public native void slideDown(Element e) /*-{
        $wnd.jQuery(e).fadeIn('slow');
    }-*/;

    @Override
    public native void slideUp(Element e) /*-{
        $wnd.jQuery(e).fadeIn('slow');
    }-*/;

}
