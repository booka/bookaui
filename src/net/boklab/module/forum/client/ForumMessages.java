package net.boklab.module.forum.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("es")
public interface ForumMessages extends Messages {

    @DefaultMessage("Discusiones")
    String browserTitle();

    @DefaultMessage("Edición de ''{0}''")
    String locationForum(String title);

    @DefaultMessage("Abriendo edición de ''{0}''")
    String openForum(String title);

    @DefaultMessage("Abriendo edición...")
    String openForumUntitled();

    @DefaultMessage("edicion")
    String resourceForum();

    @DefaultMessage("Guardando edición...")
    String update();

}
