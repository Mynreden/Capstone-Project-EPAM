package com.example.capstoneproject.config;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.springframework.stereotype.Component;

@Component
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("Session Created with session id: " + se.getSession().getId());
        se.getSession().setMaxInactiveInterval(30 * 60); // 30 minutes
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("Session Destroyed, Session id: " + se.getSession().getId());
    }
}
