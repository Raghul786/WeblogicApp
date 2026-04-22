package com.example.reddit.usecase;

import java.util.List;
import java.util.Map;

public class FeedUseCase {

    private final FeedRepository repository;

    public FeedUseCase(FeedRepository repository) {
        this.repository = repository;
    }

    public List<Map<String,Object>> getCommunityFeed(long communityId) {
        return repository.fetchCommunityFeed(communityId);
    }
}

