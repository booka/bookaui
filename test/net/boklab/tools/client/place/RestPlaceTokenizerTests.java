package net.boklab.tools.client.place;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

public class RestPlaceTokenizerTests {

    private RestPlaceTokenizer tokenizer;

    @Before
    public void setup() {
	this.tokenizer = new RestPlaceTokenizer();
    }

    @Test
    public void shouldGetControllerAndIdRequest() {
	Place place = tokenizer.fromString("/archives/1");
	assertEquals("archives", place.getParameter(Place.CONTROLLER));
	assertEquals("1", place.getParameter(Place.ID));
    }

    @Test
    public void shouldGetControllerRequest() {
	Place place = tokenizer.fromString("/archives");
	assertEquals("archives", place.getParameter(Place.CONTROLLER));
	assertNull(place.getParameter(Place.ID));
    }

    @Test
    public void shouldGetEmptyRequest() {
	Place place = tokenizer.fromString("");
	assertSame(Place.ROOT, place);
	place = tokenizer.fromString("/");
	assertSame(Place.ROOT, place);
    }

    @Test
    public void shouldGetEmptyToken() {
	Place place = new Place();
	assertEquals("/", tokenizer.toString(place));
    }

    @Test
    public void shouldGetTokenWithController() {
	Place place = new Place("archives");
	assertEquals("/archives", tokenizer.toString(place));
    }

    @Test
    public void shouldGetTokenWithControllerAndId() {
	Place place = new Place("archives", "1");
	assertEquals("/archives/1", tokenizer.toString(place));
    }

}
