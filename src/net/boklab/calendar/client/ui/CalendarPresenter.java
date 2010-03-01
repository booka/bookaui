package net.boklab.calendar.client.ui;

import net.boklab.tools.client.mvp.AbstractPresenter;
import net.boklab.tools.client.router.Router;

import com.allen_sauer.gwt.log.client.Log;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class CalendarPresenter extends AbstractPresenter<CalendarDisplay> {
    static final String MESES[] = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
	    "Septiembre", "Octubre", "Noviembre", "Diciembre" };

    @Inject
    public CalendarPresenter(final Router router, final Provider<CalendarDisplay> provider) {
	super(provider);

	final CalendarDisplay calendar = provider.get();
	for (int mes = 0; mes < 12; mes++) {
	    calendar.setHTML(mes, 0, ">" + MESES[mes]);
	    for (int dia = 1; dia < 32; dia++) {
		calendar.setHTML(mes, dia, ">" + dia);
	    }
	}
	Log.debug("JODER!");
    }
}
