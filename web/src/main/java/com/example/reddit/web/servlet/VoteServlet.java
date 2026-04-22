package com.example.reddit.web.servlet;

import com.example.reddit.web.config.AppWiring;
import com.example.reddit.usecase.VoteUseCase;
import com.example.reddit.usecase.UserService;
import com.example.reddit.web.util.CurrentUser;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class VoteServlet extends HttpServlet {

    private VoteUseCase voteUseCase;
    private UserService userService;

    @Override
    public void init() {
        voteUseCase = AppWiring.voteUseCase();
        userService = AppWiring.userService();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String username = CurrentUser.get(req);   // this is a String in your app
        long userId = userService.findUserIdByUsername(username);

        long postId = Long.parseLong(req.getParameter("postId"));
        int value = Integer.parseInt(req.getParameter("value"));

        voteUseCase.votePost(userId, postId, value);

        resp.setStatus(204);
    }
}

