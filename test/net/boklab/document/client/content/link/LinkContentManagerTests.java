package net.boklab.document.client.content.link;

import static org.junit.Assert.assertEquals;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.boklab.document.client.content.link.regex.PatternFactory;
import net.boklab.document.client.content.link.regex.PatternMatcher;

import org.junit.Before;
import org.junit.Test;

public class LinkContentManagerTests {

    public static class JavaPattern implements PatternMatcher {
	private static final String[] EMPTY = new String[0];
	private final Pattern pattern;

	public JavaPattern(final String regex, final int flags) {
	    pattern = Pattern.compile(regex, flags);
	}

	@Override
	public String[] match(final String text) {
	    final Matcher matcher = pattern.matcher(text);
	    if (!matcher.matches()) {
		return EMPTY;
	    }
	    final int groupCount = matcher.groupCount();
	    final String[] result = new String[groupCount + 1];
	    for (int index = 0; index <= groupCount; index++) {
		result[index] = matcher.group(index);
	    }

	    return result;
	}

    }

    private LinkContentManager manager;

    @Before
    public void setup() {
	manager = new LinkContentManager(new PatternFactory() {
	    @Override
	    public PatternMatcher create(final String regex, final int flags) {
		return new JavaPattern(regex, flags);
	    }
	});
    }

    @Test
    public void shouldRenderDailyMotion() {
	assertRender(
		"<p><label>title</label><object type=\"application/x-shockwave-flash\" data=\"http://www.dailymotion.com/swf/x8bob0_fleet-foxes-mykonos_music&related=0\" width=\"500\" height=\"300\"><param name=\"movie\" value=\"http://www.dailymotion.com/swf/x8bob0_fleet-foxes-mykonos_music&related=0\"></param><param name=\"allowFullScreen\" value=\"true\"></param><param name=\"allowScriptAccess\" value=\"always\"></param><a href=\"http://www.dailymotion.com/video/x8bob0_fleet-foxes-mykonos_music?embed=1\"><img src=\"http://www.dailymotion.com/thumbnail/video/x8bob0_fleet-foxes-mykonos_music\" width=\"500\" height=\"300\"/></a></object></p>",
		"title",
		"http://www.dailymotion.com/relevance/search/fleet+foxes/video/x8bob0_fleet-foxes-mykonos_music");
    }

    @Test
    public void shouldRenderGoogleVideo() {
	assertRender(
		"<p><label>title</label><object width=\"500\" height=\"300\"><param name=\"movie\" value=\"http://video.google.com/googleplayer.swf?docid=7442132741322615356&hl=en&fs=true\"></param><param name=\"wmode\" value=\"transparent\"></param><embed src=\"http://video.google.com/googleplayer.swf?docid=7442132741322615356\" type=\"application/x-shockwave-flash\" wmode=\"transparent\" width=\"500\" height=\"300\"></embed></object></p>",
		"title", "http://video.google.com/videoplay?docid=7442132741322615356");
    }

    @Test
    public void shouldRenderImage() {
	assertRender(
		"<p><label>title</label><img src=\"http://farm4.static.flickr.com/3171/2622922160_afc2ca4cff.jpg\" alt=\"title\"/></p>",
		"title", "http://farm4.static.flickr.com/3171/2622922160_afc2ca4cff.jpg");
    }

    @Test
    public void shouldRenderLinks() {
	assertRender("<p><label>title</label><a href=\"http://rubyonrails.org\">title</a></p>", "title",
		"http://rubyonrails.org");
	assertRender("<p><a href=\"http://rubyonrails.org\">http://rubyonrails.org</a></p>", "",
		"http://rubyonrails.org");
    }

    @Test
    public void shouldRenderVimeo() {
	assertRender(
		"<p><label>title</label><object width=\"500\" height=\"300\"><param name=\"allowfullscreen\" value=\"true\" /><param name=\"allowscriptaccess\" value=\"always\" /><param name=\"movie\" value=\"http://vimeo.com/moogaloop.swf?clip_id=9773538&amp;server=vimeo.com&amp;show_title=0&amp;show_byline=0&amp;show_portrait=0&amp;color=00adef&amp;fullscreen=1\" /><embed src=\"http://vimeo.com/moogaloop.swf?clip_id=9773538&amp;server=vimeo.com&amp;show_title=0&amp;show_byline=0&amp;show_portrait=0&amp;color=00adef&amp;fullscreen=1\" type=\"application/x-shockwave-flash\" allowfullscreen=\"true\" allowscriptaccess=\"always\" width=\"500\" height=\"300\"></embed></object></p>",
		"title", "http://vimeo.com/9773538");
    }

    @Test
    public void shouldRenderYouTube() {
	assertRender(
		"<p><label>title</label><object width=\"425\" height=\"344\"><param name=\"movie\" value=\"http://www.youtube.com/v/8JMO-hxYRq4\"></param><param name=\"wmode\" value=\"transparent\"></param><embed src=\"http://www.youtube.com/v/8JMO-hxYRq4\" type=\"application/x-shockwave-flash\" wmode=\"transparent\" width=\"425\" height=\"344\"></embed></object></p>",
		"title", "http://www.youtube.com/watch?v=8JMO-hxYRq4");
    }

    private void assertRender(final String expected, final String title, final String body) {
	final String result = manager.render(title, body);
	assertEquals(expected, result);
    }

}
