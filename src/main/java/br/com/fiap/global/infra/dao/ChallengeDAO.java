package br.com.fiap.global.infra.dao;

import br.com.fiap.global.model.Challenge; // Importa o modelo Challenge

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
public class ChallengeDAO {

    @Inject
    DataSource dataSource;

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * Insere um novo desafio no banco de dados.
     */
    public void save(Challenge challenge) throws SQLException {
        // Colunas do SQL atualizadas para corresponder ao modelo
        String sql = "INSERT INTO challenges (title, description, difficulty, reward_points) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, challenge.getTitle());        // getTitle()
            stmt.setString(2, challenge.getDescription());
            stmt.setString(3, challenge.getDifficulty());   // getDifficulty()
            stmt.setInt(4, challenge.getRewardPoints());    // getRewardPoints()

            stmt.executeUpdate();
            conn.commit();
        }
    }

    /**
     * Utilitário para mapear um ResultSet para um objeto Challenge.
     */
    private Challenge mapChallenge(ResultSet rs) throws SQLException {
        Challenge challenge = new Challenge();
        challenge.setId(rs.getInt("id"));
        challenge.setTitle(rs.getString("title"));
        challenge.setDescription(rs.getString("description"));
        challenge.setDifficulty(rs.getString("difficulty"));
        challenge.setRewardPoints(rs.getInt("reward_points"));
        return challenge;
    }

    /**
     * Busca todos os desafios no banco de dados.
     */
    public List<Challenge> findAll() throws SQLException {
        List<Challenge> challenges = new ArrayList<>();
        // Colunas do SQL atualizadas para corresponder ao modelo
        String sql = "SELECT id, title, description, difficulty, reward_points FROM challenges ORDER BY reward_points ASC";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                challenges.add(mapChallenge(rs));
            }
        }
        return challenges;
    }
}