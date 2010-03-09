package net.boklab.indices.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class BokIndicesEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
	I18nIndices.t = GWT.create(IndicesMessages.class);
    }

}
