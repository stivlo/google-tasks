package org.obliquid.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

/**
 * Url Encoder with UTF-8 default (the only accepted by W3C), and with
 * UnsupportedEncodedException silenced. Unsupported UTF-8 can be a JDK bug,
 * which should not happen. It's logged anyway.
 * 
 * @author stivlo
 * 
 */
public class MyUrlEncoder {

    /** Logger instance */
    private static final Logger log = Logger.getLogger(MyUrlEncoder.class);

    /**
     * Encode a URL
     * 
     * @param url
     *            URL to be encoded
     * @return encoded URL
     */
    public static String encode(String url) {
        String encodedUrl = null;
        try {
            encodedUrl = URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            log.error("UTF-8 unsupported? JDK Bug?", ex);
        }
        return encodedUrl;
    }

}
