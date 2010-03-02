package net.boklab.document.client.content.link;

import java.util.ArrayList;

import net.boklab.document.client.content.link.regex.JSPattern;
import net.boklab.document.client.content.link.regex.PatternFactory;
import net.boklab.document.client.content.link.regex.PatternMatcher;
import net.boklab.tools.client.Empty;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class LinkContentManager {
    public static interface LinkFilter {
	public String getRegex();

	public String render(String title, String[] groups);
    }
    private final ArrayList<LinkFilter> filters;
    private final ArrayList<PatternMatcher> patterns;
    private final PatternFactory factory;

    @Inject
    public LinkContentManager() {
	this(new PatternFactory() {
	    @Override
	    public PatternMatcher create(final String regex, final int flags) {
		return new JSPattern(regex, flags);
	    }
	});
    }

    public LinkContentManager(final PatternFactory factory) {
	this.factory = factory;
	filters = new ArrayList<LinkFilter>();
	patterns = new ArrayList<PatternMatcher>();
	// IMAGE
	add(new LinkFilter() {
	    @Override
	    public String getRegex() {
		return "http:\\/\\/.+\\.(jpg|jpeg|bmp|gif|png)(\\?\\S+)?";
	    }

	    @Override
	    public String render(final String title, final String[] groups) {
		return "<img src=\"" + groups[0] + "\" alt=\"" + title + "\"/>";
	    }
	});
	// YOUTUBE
	add(new LinkFilter() {
	    @Override
	    public String getRegex() {
		return "http:\\/\\/(www.)?youtube\\.com\\/watch\\?v=([A-Za-z0-9._%-]*)(\\&\\S+)?";
	    }

	    @Override
	    public String render(final String title, final String[] groups) {
		final int width = 425;
		final int height = 344;
		final String id = groups[2];
		return "<object width=\""
			+ width
			+ "\" height=\""
			+ height
			+ "\"><param name=\"movie\" value=\"http://www.youtube.com/v/"
			+ id
			+ "\"></param><param name=\"wmode\" value=\"transparent\"></param><embed src=\"http://www.youtube.com/v/"
			+ id + "\" type=\"application/x-shockwave-flash\" wmode=\"transparent\" width=\"" + width
			+ "\" height=\"" + height + "\"></embed></object>";
	    }
	});

	// VIMEO
	add(new LinkFilter() {
	    @Override
	    public String getRegex() {
		return "http:\\/\\/(www.)?vimeo\\.com\\/([A-Za-z0-9._%-]*)((\\?|#)\\S+)?";
	    }

	    @Override
	    public String render(final String title, final String[] groups) {
		final int width = 500;
		final int height = 300;
		final String id = groups[2];
		final String showTitle = "0";
		return "<object width=\""
			+ width
			+ "\" height=\""
			+ height
			+ "\"><param name=\"allowfullscreen\" value=\"true\" /><param name=\"allowscriptaccess\" value=\"always\" /><param name=\"movie\" value=\"http://vimeo.com/moogaloop.swf?clip_id="
			+ id
			+ "&amp;server=vimeo.com&amp;show_title="
			+ showTitle
			+ "&amp;show_byline=0&amp;show_portrait=0&amp;color=00adef&amp;fullscreen=1\" /><embed src=\"http://vimeo.com/moogaloop.swf?clip_id="
			+ id
			+ "&amp;server=vimeo.com&amp;show_title="
			+ showTitle
			+ "&amp;show_byline=0&amp;show_portrait=0&amp;color=00adef&amp;fullscreen=1\" type=\"application/x-shockwave-flash\" allowfullscreen=\"true\" allowscriptaccess=\"always\" width=\""
			+ width + "\" height=\"" + height + "\"></embed></object>";
	    }

	});

	// GOOGLE VIDEO
	add(new LinkFilter() {
	    @Override
	    public String getRegex() {
		return "http:\\/\\/video\\.google\\.com\\/videoplay\\?docid\\=(-?[0-9]*)[&\\w;=\\+_\\-]*";
	    }

	    @Override
	    public String render(final String title, final String[] groups) {
		final int width = 500;
		final int height = 300;
		final String id = groups[1];
		return "<object width=\""
			+ width
			+ "\" height=\""
			+ height
			+ "\"><param name=\"movie\" value=\"http://video.google.com/googleplayer.swf?docid="
			+ id
			+ "&hl=en&fs=true\"></param><param name=\"wmode\" value=\"transparent\"></param><embed src=\"http://video.google.com/googleplayer.swf?docid="
			+ id + "\" type=\"application/x-shockwave-flash\" wmode=\"transparent\" width=\"" + width
			+ "\" height=\"" + height + "\"></embed></object>";
	    }
	});

	// DAILY MOTION
	add(new LinkFilter() {

	    @Override
	    public String getRegex() {
		return "http:\\/\\/www\\.dailymotion\\.com.*\\/video\\/(.+)_*";
	    }

	    @Override
	    public String render(final String title, final String[] groups) {
		final int width = 500;
		final int height = 300;
		final String id = groups[1];
		return "<object type=\"application/x-shockwave-flash\" data=\"http://www.dailymotion.com/swf/"
			+ id
			+ "&related=0\" width=\""
			+ width
			+ "\" height=\""
			+ height
			+ "\"><param name=\"movie\" value=\"http://www.dailymotion.com/swf/"
			+ id
			+ "&related=0\"></param><param name=\"allowFullScreen\" value=\"true\"></param><param name=\"allowScriptAccess\" value=\"always\"></param><a href=\"http://www.dailymotion.com/video/"
			+ id + "?embed=1\"><img src=\"http://www.dailymotion.com/thumbnail/video/" + id + "\" width=\""
			+ width + "\" height=\"" + height + "\"/></a></object>";
	    }
	});

	// LINKS
	add(new LinkFilter() {
	    @Override
	    public String getRegex() {
		return "http://(.+)";
	    }

	    @Override
	    public String render(final String title, final String[] groups) {
		final String body = Empty.is(title) ? groups[0] : title;
		return "<a href=\"" + groups[0] + "\">" + body + "</a>";
	    }

	});

	// PLAIN
	add(new LinkFilter() {
	    @Override
	    public String getRegex() {
		return "(.*)";
	    }

	    @Override
	    public String render(final String title, final String[] groups) {
		return groups[0];
	    }

	});

    }

    public void add(final LinkFilter linkFilter) {
	filters.add(linkFilter);
	patterns.add(factory.create(linkFilter.getRegex(), JSPattern.CASE_INSENSITIVE));
    }

    public String render(final String title, final String body) {
	String[] groups;
	final int total = patterns.size();
	for (int index = 0; index < total; index++) {
	    groups = patterns.get(index).match(body);
	    if (groups.length > 1) {
		final String rendered = filters.get(index).render(title, groups);
		final String label = Empty.is(title) ? "" : "<label>" + title + "</label>";
		return "<p>" + label + rendered + "</p>";
	    }
	}
	return "";
    }
}
