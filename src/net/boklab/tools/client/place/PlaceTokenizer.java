package net.boklab.tools.client.place;

public interface PlaceTokenizer {
    Place fromString(String token);

    String toString(Place place);
}
