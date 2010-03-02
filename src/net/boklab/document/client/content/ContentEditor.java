package net.boklab.document.client.content;

import net.boklab.core.client.model.Bok;
import net.boklab.tools.client.mvp.Display;
import net.boklab.tools.client.mvp.Presenter;

public interface ContentEditor<T extends Display> extends Presenter<T> {

    Bok getBok();

    void setBok(Bok bok);

    void updateClip();

}
