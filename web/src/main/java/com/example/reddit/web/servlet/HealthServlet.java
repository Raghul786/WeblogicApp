package com.example.reddit.web.servlet;

import com.example.reddit.web.config.AppWiring;
import com.example.reddit.usecase.HealthService;
import com.example.reddit.usecase.HealthStatus;

import javax.servlet.http.*;
import java.io.PrintWriter;
import java.io.IOException;

public class HealthServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();

        HealthService service = AppWiring.healthService();
        HealthStatus status = service.check();

        out.println("DB UP: " + status.isDbUp());
        out.println("Heap used MB: " + status.getHeapUsedMb());
        out.println("Heap max MB: " + status.getHeapMaxMb());
    }
}

