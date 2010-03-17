package net.boklab.module.calendar.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

public class CalendarWidget extends Composite implements CalendarDisplay {

    interface CalendarWidgetUiBinder extends UiBinder<Widget, CalendarWidget> {
    }

    private static CalendarWidgetUiBinder uiBinder = GWT.create(CalendarWidgetUiBinder.class);
    static final String MESES[] = { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio",
	    "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };

    @UiField
    SimplePanel content;

    private final FlexTable grid;

    public CalendarWidget() {
	initWidget(uiBinder.createAndBindUi(this));
	grid = new FlexTable();
	for (int mes = 0; mes < 12; mes++) {
	    grid.setHTML(mes, 0, MESES[mes]);
	    for (int dia = 1; dia < 32; dia++) {
		grid.setHTML(mes, dia, "" + dia);
	    }
	}
	content.setWidget(grid);
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public void setHTML(int row, int column, String html) {
	grid.setHTML(row, column, html);
    }

}
