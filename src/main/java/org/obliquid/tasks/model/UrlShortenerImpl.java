package org.obliquid.tasks.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;
import org.obliquid.tasks.interfaces.UrlShortener;

import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;

/** 
 * I'm not really interested in URL shortener API, it's here just to test OAuth 2 authentication.
 * The original sample program is http://code.google.com/p/google-api-java-client/wiki/OAuth2Draft10
 * I've created objects and interfaces with Spring for the Authorization part, this part could be
 * improved, but I am not interested in it, I would like to take a look to the Tasks API instead.
 * 
 * @author stivlo
 *
 */
public class UrlShortenerImpl implements UrlShortener {

    private HttpRequestFactory rf;

    /** Logger instance */
    private static final Logger log = Logger.getLogger(UrlShortenerImpl.class);
    
    @Override
    public void setHttpRequestFactory(HttpRequestFactory requestFactory) {
        this.rf = requestFactory;
    }

    @Override
    public String shorten(final String url) {
        GenericUrl shortenEndpoint = new GenericUrl("https://www.googleapis.com/urlshortener/v1/url");
        String requestBody =  "{\"longUrl\":\"http://farm6.static.flickr.com/5281/5686001474_e06f1587ff_o.jpg\"}";
        HttpRequest request;
        try {
            request = rf.buildPostRequest(shortenEndpoint, new ByteArrayContent(requestBody));
            request.headers.contentType = "application/json";
            HttpResponse shortUrl = request.execute();
            BufferedReader output = new BufferedReader(new InputStreamReader(shortUrl.getContent()));
            System.out.println("Shorten Response: ");
            for (String line = output.readLine(); line != null; line = output.readLine()) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            log.error("shorten()", ex);
        }
        return null;
    }

}
