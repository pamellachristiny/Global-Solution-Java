package br.com.fiap.global.model;

/**
 * Data Transfer Object (DTO) para transferir informações de progresso
 * do desafio juntamente com o título do desafio (que vem de outra tabela).
 */
public class ProgressInfoDTO {
    private int progressId;
    private String challengeTitle;
    private int progressPercent;
    private String status;

    // Construtor
    public ProgressInfoDTO(int progressId, String challengeTitle, int progressPercent, String status) {
        this.progressId = progressId;
        this.challengeTitle = challengeTitle;
        this.progressPercent = progressPercent;
        this.status = status;
    }

    // Getters (para acessar os dados)
    public int getProgressId() { return progressId; }
    public String getChallengeTitle() { return challengeTitle; }
    public int getProgressPercent() { return progressPercent; }
    public String getStatus() { return status; }
}