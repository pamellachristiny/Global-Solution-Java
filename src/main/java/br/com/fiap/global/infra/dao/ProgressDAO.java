package br.com.fiap.global.infra.dao;

import br.com.fiap.global.model.Progress;
import br.com.fiap.global.model.ProgressInfoDTO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProgressDAO {

    @Inject
    DataSource dataSource;

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void startChallenge(Progress progress) throws SQLException {
        String sql = "INSERT INTO progress (user_id, challenge_id, status, progress_percent) VALUES (?, ?, 'in_progress', 0)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, progress.getUserId());
            stmt.setInt(2, progress.getChallengeId());

            stmt.executeUpdate();
            conn.commit();
        }
    }

    public boolean hasProgress(int userId, int challengeId) throws SQLException {
        String sql = "SELECT 1 FROM progress WHERE user_id = ? AND challenge_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, challengeId);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    public boolean updateProgress(int progressId, int userId, int newPercent) throws SQLException {
        String sql;
        String status = (newPercent == 100) ? "completed" : "in_progress";

        if (newPercent == 100) {
            sql = "UPDATE progress SET progress_percent = ?, status = ?, completed_at = SYSTIMESTAMP WHERE id = ? AND user_id = ?";
        } else {
            sql = "UPDATE progress SET progress_percent = ?, status = ? WHERE id = ? AND user_id = ?";
        }

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, newPercent);
            stmt.setString(2, status);
            stmt.setInt(3, progressId);
            stmt.setInt(4, userId);

            int affectedRows = stmt.executeUpdate();
            conn.commit();
            return affectedRows > 0;
        }
    }

    public List<ProgressInfoDTO> findUserProgress(int userId) throws SQLException {
        String sql = """
            SELECT p.id, p.progress_percent, p.status, c.title
            FROM progress p JOIN challenges c ON p.challenge_id = c.id
            WHERE p.user_id = ?
            """;

        List<ProgressInfoDTO> progressList = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    progressList.add(new ProgressInfoDTO(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getInt("progress_percent"),
                            rs.getString("status")
                    ));
                }
            }
        }
        return progressList;
    }
}