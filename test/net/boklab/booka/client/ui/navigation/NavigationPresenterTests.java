package net.boklab.booka.client.ui.navigation;

import net.boklab.testing.BookaTester;
import net.boklab.workspace.client.ui.navigation.NavigationPresenter;

import org.junit.Before;

public class NavigationPresenterTests {

    @Before
    public void setup() {
	BookaTester tester = new BookaTester();
	NavigationPresenter presenter = tester.get(NavigationPresenter.class);
    }

}
