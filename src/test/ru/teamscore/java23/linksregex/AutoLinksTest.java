package ru.teamscore.java23.linksregex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AutoLinksTest {
    @Test
    void isHtmlLink() {
        String[] wrongUrls = {
                "https",
                "http://",
                "ftp://example.com",
                "example",
                "example.",
                ".com",
                "/1",
                "http://a/1",
                ".com/test",
                "gdfd/gfhfhg1/sdfks7567djf.df567sldf/65756",
        };
        String[] correctUrls = {
                "example.com",
                "qwertyuiopasdfghjklzxcvbnm@:%._+~#=0123456789.asdfg0",
                "http://example.com",
                "https://example.com",
                "//example.com",
                "http://example.com/path",
                "http://example.com/path/file001",
                "http://example.com/path/file001.ext",
                "http://example.com/path/file001.ext?param",
                "http://example.com/path/file001.ext?param=value"
        };
        for (String url : wrongUrls) {
            assertEquals(false, AutoLinks.isHtmlLink(url), url);
        }
        for (String url : correctUrls) {
            assertEquals(true, AutoLinks.isHtmlLink(url), url);
        }    }

    @Test
    void insertHtmlLinksNoUrls() {
        String text = "Some text. With dots/slashes.";
        assertEquals(text, AutoLinks.insertHtmlLinks(text));
    }

    @Test
    void insertHtmlLinksOneUrl() {
        String text = "Мой сайт: example.com/mypage/index.html";
        String linkedText = "Мой сайт: <a href=\"example.com/mypage/index.html\">example.com/mypage/index.html</a>";
        assertEquals(linkedText, AutoLinks.insertHtmlLinks(text));
    }

    @Test
    void insertHtmlLinksOnlyUrl() {
        String text = "example.com/mypage/index.html?h=12&b";
        String linkedText = "<a href=\"example.com/mypage/index.html?h=12&b\">example.com/mypage/index.html?h=12&b</a>";
        assertEquals(linkedText, AutoLinks.insertHtmlLinks(text));
    }

    @Test
    void insertHtmlLinksFewUrls() {
        //Source: https://developer.mozilla.org/en-US/docs/Learn/Common_questions/Web_mechanics/What_is_a_URL
        String text = """
#Examples of absolute URLs
Full URL (the same as the one we used before)\thttps://developer.mozilla.org/en-US/docs/Learn

Implicit protocol\t//developer.mozilla.org/en-US/docs/Learn
In this case, the browser will call that URL with the same protocol as the one used to load the document hosting that URL.
Implicit domain name\t/en-US/docs/Learn
his is the most common use case for an absolute URL within an HTML document. The browser will use the same protocol and the same domain name as the one used to load the document hosting that URL. Note: it isn't possible to omit the domain name without omitting the protocol as well.

#Examples of relative URLs
To better understand the following examples, let's assume that the URLs are called from within the document located at the following URL: https://developer.mozilla.org/en-US/docs/Learn

Sub-resources\tSkills/Infrastructure/Understanding_URLs
Because that URL does not start with /, the browser will attempt to find the document in a subdirectory of the one containing the current resource. So in this example, we really want to reach this URL: https://developer.mozilla.org/en-US/docs/Learn/Skills/Infrastructure/Understanding_URLs.
Going back in the directory tree\t../CSS/display
In this case, we use the ../ writing convention — inherited from the UNIX file system world — to tell the browser we want to go up from one directory. Here we want to reach this URL: https://developer.mozilla.org/en-US/docs/Learn/../CSS/display, which can be simplified to: https://developer.mozilla.org/en-US/docs/CSS/display.
""";
        String linkedText = """
#Examples of absolute URLs
Full URL (the same as the one we used before)\t<a href="https://developer.mozilla.org/en-US/docs/Learn">https://developer.mozilla.org/en-US/docs/Learn</a>

Implicit protocol\t<a href="//developer.mozilla.org/en-US/docs/Learn">//developer.mozilla.org/en-US/docs/Learn</a>
In this case, the browser will call that URL with the same protocol as the one used to load the document hosting that URL.
Implicit domain name\t/en-US/docs/Learn
his is the most common use case for an absolute URL within an HTML document. The browser will use the same protocol and the same domain name as the one used to load the document hosting that URL. Note: it isn't possible to omit the domain name without omitting the protocol as well.

#Examples of relative URLs
To better understand the following examples, let's assume that the URLs are called from within the document located at the following URL: <a href="https://developer.mozilla.org/en-US/docs/Learn">https://developer.mozilla.org/en-US/docs/Learn</a>

Sub-resources\tSkills/Infrastructure/Understanding_URLs
Because that URL does not start with /, the browser will attempt to find the document in a subdirectory of the one containing the current resource. So in this example, we really want to reach this URL: <a href="https://developer.mozilla.org/en-US/docs/Learn/Skills/Infrastructure/Understanding_URLs.">https://developer.mozilla.org/en-US/docs/Learn/Skills/Infrastructure/Understanding_URLs.</a>
Going back in the directory tree\t../CSS/display
In this case, we use the ../ writing convention — inherited from the UNIX file system world — to tell the browser we want to go up from one directory. Here we want to reach this URL: <a href="https://developer.mozilla.org/en-US/docs/Learn/../CSS/display">https://developer.mozilla.org/en-US/docs/Learn/../CSS/display</a>, which can be simplified to: <a href="https://developer.mozilla.org/en-US/docs/CSS/display.">https://developer.mozilla.org/en-US/docs/CSS/display.</a>
""";
        assertEquals(linkedText, AutoLinks.insertHtmlLinks(text));
    }
}