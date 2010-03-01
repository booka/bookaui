package net.boklab.document.client.content;

import net.boklab.core.client.model.Bok;
import net.boklab.tools.client.mvp.Presenter;

public interface ContentEditor<T extends ContentTypeEditorDisplay> extends Presenter<T> {

    Bok getBok();

    void setBok(Bok clip);

    void updateClip();

}
