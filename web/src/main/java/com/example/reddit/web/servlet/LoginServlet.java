package com.example.reddit.web.servlet;

import com.example.reddit.infra.dao.JdbcAuthRepository;
import com.example.reddit.infra.datasource.DataSourceProvider;
import com.example.reddit.infra.security.BCryptPasswordHasher;
import com.example.reddit.usecase.LoginUseCase;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        LoginUseCase loginUseCase = new LoginUseCase(
                new JdbcAuthRepository(DataSourceProvider.getDataSource()),
                new BCryptPasswordHasher()
        );

        boolean authenticated = loginUseCase.authenticate(username, password);

        if (authenticated) {

            HttpSession session = req.getSession(true);
            session.setAttribute("user", username);

            // ✅ context aware redirect
            resp.sendRedirect(req.getContextPath() + "/reddit/users/count");

        } else {
            resp.sendRedirect(req.getContextPath() + "/login.html?error=1");
        }
    }
}

