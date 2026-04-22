package com.example.reddit.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CurrentUser {

    public static String get(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session == null) return null;

        Object user = session.getAttribute("user");

        return user != null ? user.toString() : null;
    }
}

