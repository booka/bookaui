package net.boklab.document.client.ui.clip;

import net.boklab.document.client.model.Clip;
import net.boklab.tools.client.mvp.AbstractPresenter;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ClipPresenter extends AbstractPresenter<ClipDisplay> {

    @Inject
    public ClipPresenter(Provider<ClipDisplay> provider) {
	super(provider);
    }

    public void setClip(Clip clip) {
	getDisplay().getBody().setHTML(clip.getBody());
    }

}
