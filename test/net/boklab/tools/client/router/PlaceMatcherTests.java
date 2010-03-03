package net.boklab.tools.client.router;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import net.boklab.tools.client.place.Place;

import org.junit.Test;

public class PlaceMatcherTests {

    @Test
    public void shouldMatch() {
	final Place entrance = new Place("entrance");
	assertEquals("/entrance", entrance.placeToken);
	assertTrue(new PlaceMatcher("^/entrance$", null).matches(entrance));
    }
}
