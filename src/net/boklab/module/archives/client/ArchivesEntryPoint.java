package net.boklab.module.archives.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class ArchivesEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
	I18nArchives.t = GWT.create(ArchivesMessages.class);
    }

}
