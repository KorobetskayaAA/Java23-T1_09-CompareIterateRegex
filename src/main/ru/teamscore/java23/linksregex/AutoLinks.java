package ru.teamscore.java23.linksregex;

import java.util.regex.Pattern;

public class AutoLinks {
    private AutoLinks() {}
    final static Pattern urlPattern = Pattern.compile(
            "(((https?:)?//)?[-a-zA-Z0-9@:%._+~#=]{1,256}\\.[a-zA-Z0-9]{1,6}\\b([-a-zA-Z0-9()@:%_+.~#?&/=]*))"
    );

    public static boolean isHtmlLink(String text) {
        var urlMatcher = urlPattern.matcher(text);
        return urlMatcher.matches();
    }

    public static String insertHtmlLinks(String text) {
        var urlMatcher = urlPattern.matcher(text);
        if (urlMatcher.find()) {
            return urlMatcher.replaceAll("<a href=\"$1\">$1</a>");
        }
        return text;
    }
}
