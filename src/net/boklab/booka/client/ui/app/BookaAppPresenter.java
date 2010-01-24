package net.boklab.booka.client.ui.app;

import net.boklab.booka.client.ui.navigation.NavigationPresenter;
import net.boklab.tools.client.mvp.AbstractPresenter;
import net.boklab.tools.client.mvp.Display;
import net.boklab.tools.client.mvp.Presenter;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class BookaAppPresenter extends AbstractPresenter<BookaAppDisplay> {

    @Inject
    public BookaAppPresenter(Provider<BookaAppDisplay> display, NavigationPresenter navigation) {
	super(display);
	getDisplay().setNavigation(navigation.getDisplay());
    }

    public void setContent(Presenter<? extends Display> presenter) {
	getDisplay().setContent(presenter.getDisplay());
    }

}
