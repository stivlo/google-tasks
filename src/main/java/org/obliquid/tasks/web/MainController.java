package org.obliquid.tasks.web;

import org.obliquid.tasks.interfaces.SessionStore;
import org.obliquid.tasks.interfaces.UrlShortener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Autowired
    private SessionStore session;

    @Autowired
    private UrlShortener shortener;

    @RequestMapping("/")
    public String defaultView(Model model) {
        model.addAttribute("isLoggedIn", session.isLoggedIn());
        if (session.isLoggedIn()) {
            shortener.setHttpRequestFactory(session.getRequestFactory());
            String shortUrl = shortener.shorten("http://www.stefanolocati.it/");
            model.addAttribute("shortUrl", shortUrl);
        }
        return "default";
    }

}
