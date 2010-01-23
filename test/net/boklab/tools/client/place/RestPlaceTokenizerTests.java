package net.boklab.tools.client.place;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RestPlaceTokenizerTests {

    private RestPlaceTokenizer tokenizer;

    @Before
    public void setup() {
	this.tokenizer = new RestPlaceTokenizer();
    }

    @Test
    public void shouldGetEmptyToken() {
	Place place = new Place();
	assertEquals("/", tokenizer.getToken(place));
    }

    @Test
    public void shouldGetTokenWithController() {
	Place place = new Place("archives");
	assertEquals("/archives", tokenizer.getToken(place));
    }

    @Test
    public void shouldGetTokenWithControllerAndId() {
	Place place = new Place("archives", "1");
	assertEquals("/archives/1", tokenizer.getToken(place));
    }

}
