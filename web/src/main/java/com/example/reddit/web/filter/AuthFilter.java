package com.example.reddit.web.filter;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String context = req.getContextPath();
        String path = req.getRequestURI().substring(context.length());

        // ===== Public paths (no auth required) =====
        if (
                path.equals("/login") ||
                path.equals("/login.html") ||
                path.startsWith("/health") ||
                path.startsWith("/css") ||
                path.startsWith("/js")
        ) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession(false);
        boolean loggedIn =
                session != null &&
                session.getAttribute("user") != null;

        if (!loggedIn) {
            resp.sendRedirect(context + "/login.html");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void destroy() {}
}

