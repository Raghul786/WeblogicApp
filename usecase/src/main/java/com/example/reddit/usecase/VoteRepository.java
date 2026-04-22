package com.example.reddit.usecase;

public interface VoteRepository {

    void upsertPostVote(long userId, long postId, int value);

}

