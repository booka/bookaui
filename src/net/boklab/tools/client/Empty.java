package net.boklab.tools.client;

public class Empty {

    private static final String REGEX = "^\\s*$";
    public static final String STRING = "";

    public static boolean is(final String string) {
	return string == null || string.matches(REGEX);
    }

}
