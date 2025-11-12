package br.com.fiap.global.model;

public class Challenge {
    private int id;
    private String title;
    private String description;
    private String difficulty; // easy, medium, hard
    private int rewardPoints;

    public Challenge() {}

    public Challenge(int id, String title, String description, String difficulty, int rewardPoints) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.rewardPoints = rewardPoints;
    }

    public Challenge(String title, String description, String difficulty, int rewardPoints) {
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.rewardPoints = rewardPoints;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    public int getRewardPoints() { return rewardPoints; }
    public void setRewardPoints(int rewardPoints) { this.rewardPoints = rewardPoints; }

    @Override
    public String toString() {
        return String.format("[%d] %s (%s) - %d pts", id, title, difficulty, rewardPoints);
    }
}