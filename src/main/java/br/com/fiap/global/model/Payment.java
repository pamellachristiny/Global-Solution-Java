package br.com.fiap.global.model;

import java.math.BigDecimal; // << IMPORT FALTANTE CORRIGIDO
import java.time.LocalDateTime; // Import para o campo paidAt

public class Payment {
    private int id;
    private int userId;
    private String plan;
    private BigDecimal amount;
    private LocalDateTime paidAt;

    public Payment() {}

    public Payment(int id, int userId, String plan, BigDecimal amount, LocalDateTime paidAt) {
        this.id = id;
        this.userId = userId;
        this.plan = plan;
        this.amount = amount;
        this.paidAt = paidAt;
    }

    public Payment(int userId, String plan, BigDecimal amount) {
        this.userId = userId;
        this.plan = plan;
        this.amount = amount;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getPlan() { return plan; }
    public void setPlan(String plan) { this.plan = plan; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public LocalDateTime getPaidAt() { return paidAt; }
    public void setPaidAt(LocalDateTime paidAt) { this.paidAt = paidAt; }
}