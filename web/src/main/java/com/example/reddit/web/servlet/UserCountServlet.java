package com.example.reddit.web.servlet;

import com.example.reddit.web.config.AppWiring;
import com.example.reddit.web.util.CurrentUser;
import com.example.reddit.usecase.UserService;

import javax.servlet.http.*;
import java.io.PrintWriter;
import java.io.IOException;

public class UserCountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        try {
            String currentUser = CurrentUser.get(req);

            UserService service = AppWiring.userService();
            int count = service.getUserCount();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Dashboard</title>");
            out.println("<link rel='stylesheet' href='" + req.getContextPath() + "/css/style.css'>");
            out.println("</head>");
            out.println("<body>");

            out.println("<div class='container'>");

            out.println("<h2>Welcome " + currentUser + "</h2>");
            out.println("<p>Total users: <strong>" + count + "</strong></p>");

            out.println("<form method='get' action='" + req.getContextPath() + "/logout'>");
            out.println("<button class='logout'>Logout</button>");
            out.println("</form>");

            out.println("</div>");

            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
            resp.setStatus(500);
            out.println("Internal error");
        }
    }
}

