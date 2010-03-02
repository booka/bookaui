package net.boklab.document.client.content.link.regex;

public interface PatternFactory {

    PatternMatcher create(String regex, int flags);

}
