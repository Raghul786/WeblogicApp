package com.example.reddit.web.servlet;

import com.example.reddit.web.config.AppWiring;
import com.example.reddit.usecase.FeedUseCase;

import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class FeedServlet extends HttpServlet {

    private FeedUseCase feedUseCase;

    @Override
    public void init() {
        feedUseCase = AppWiring.feedUseCase();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        long communityId = Long.parseLong(req.getParameter("communityId"));

        List<Map<String,Object>> feed = feedUseCase.getCommunityFeed(communityId);

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        out.print("[");
        boolean first = true;

        for (Map<String,Object> row : feed) {
            if (!first) out.print(",");
            first = false;

            out.print("{");
            out.print("\"postId\":" + row.get("postId") + ",");
            out.print("\"title\":\"" + row.get("title") + "\",");
            out.print("\"createdAt\":\"" + row.get("createdAt") + "\",");
            out.print("\"score\":" + row.get("score"));
            out.print("}");
        }

        out.print("]");
    }
}

