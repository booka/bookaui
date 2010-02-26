package net.boklab.document.client.content;

import net.boklab.document.client.model.Clip;
import net.boklab.tools.client.mvp.Presenter;

public interface ContentEditor<T extends ContentTypeEditorDisplay> extends Presenter<T> {

    Clip getClip();

    void setClip(Clip clip);

    void updateClip();

}
