package com.example.reddit.usecase;

import java.util.List;
import java.util.Map;

public interface FeedRepository {
    List<Map<String,Object>> fetchCommunityFeed(long communityId);
}

