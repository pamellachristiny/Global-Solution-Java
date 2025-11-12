package br.com.fiap.global.model;

import java.time.LocalDateTime;

public class Progress {
    private int id;
    private int userId;
    private int challengeId;
    private String status; // in_progress, completed
    private int progressPercent; // 0-100
    private LocalDateTime completedAt; // TIMESTAMP

    public Progress() {}

    public Progress(int id, int userId, int challengeId, String status, int progressPercent, LocalDateTime completedAt) {
        this.id = id;
        this.userId = userId;
        this.challengeId = challengeId;
        this.status = status;
        this.progressPercent = progressPercent;
        this.completedAt = completedAt;
    }

    public Progress(int userId, int challengeId) {
        this.userId = userId;
        this.challengeId = challengeId;
        this.status = "in_progress";
        this.progressPercent = 0;
        this.completedAt = null;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getChallengeId() { return challengeId; }
    public void setChallengeId(int challengeId) { this.challengeId = challengeId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public int getProgressPercent() { return progressPercent; }
    public void setProgressPercent(int progressPercent) { this.progressPercent = progressPercent; }
    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }
}