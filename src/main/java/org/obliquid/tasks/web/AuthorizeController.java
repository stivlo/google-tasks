package org.obliquid.tasks.web;

import org.obliquid.tasks.interfaces.Authorizer;
import org.obliquid.tasks.interfaces.SessionStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.api.client.http.HttpRequestFactory;

/**
 * Controller dealing with Google API authorization
 * 
 * @author stivlo
 * 
 */
@Controller
public class AuthorizeController {

    @Autowired
    private Authorizer auth;

    @Autowired
    private SessionStore session;

    @RequestMapping("/auth")
    public String sendToAuthorization() {
        return "redirect:" + auth.buildAuthorizationUrl();
    }

    @RequestMapping(value = "/auth-response", params = { "code" })
    public String authorized(@RequestParam String code) {
        HttpRequestFactory requestFactory = auth.fetchRequestFactory(code);
        session.setRequestFactory(requestFactory);
        return "redirect:/";
    }

    @RequestMapping(value = "/auth-response", params = { "error" })
    public String denied(@RequestParam String error) {
        return "denied";
    }

}
