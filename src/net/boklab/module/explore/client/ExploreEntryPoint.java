package net.boklab.module.explore.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class ExploreEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
	I18nExplore.t = GWT.create(ExploreMessages.class);
    }

}
