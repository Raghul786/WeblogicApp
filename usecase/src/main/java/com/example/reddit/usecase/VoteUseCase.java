package com.example.reddit.usecase;

public class VoteUseCase {

    private final VoteRepository repository;

    public VoteUseCase(VoteRepository repository) {
        this.repository = repository;
    }

    public void votePost(long userId, long postId, int value) {
        repository.upsertPostVote(userId, postId, value);
    }
}

