package net.boklab.tools.client.place;

public interface PlaceTokenizer {
    Place fromToken(String token);

    String getToken(Place place);
}
