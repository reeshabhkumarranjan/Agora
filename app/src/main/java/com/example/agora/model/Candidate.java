package com.example.agora.model;

public final class Candidate {
    private String name;
    private int score;

    public Candidate(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
