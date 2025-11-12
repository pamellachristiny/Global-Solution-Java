package br.com.fiap.global.infra.dao;

import br.com.fiap.global.model.Payment; // Importa o modelo Payment

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement; // Import necessário para JDBC
import java.sql.SQLException;    // Import necessário para tratamento de erro

/**
 * Data Access Object (DAO) para gerenciar a persistência de pagamentos.
 * Usa Injeção de Dependência do Quarkus (CDI) para obter a conexão.
 */
@ApplicationScoped
public class PaymentDAO {

    @Inject
    DataSource dataSource;

    // Método auxiliar para obter a conexão a partir do pool Agroal injetado
    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * Processa um pagamento: insere o registro e atualiza o plano do usuário.
     * Corresponde à lógica combinada de 'make_payment' do Python.
     */
    public void processPaymentAndUpgradeUser(Payment payment) throws SQLException {
        // Obter conexão (transação começa aqui)
        try (Connection conn = getConnection()) {

            // 1. Inserir o registro de pagamento
            String insertSql = "INSERT INTO payments (user_id, plan, amount, paid_at) VALUES (?, ?, ?, SYSTIMESTAMP)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

                insertStmt.setInt(1, payment.getUserId());
                insertStmt.setString(2, payment.getPlan());
                insertStmt.setBigDecimal(3, payment.getAmount());

                insertStmt.executeUpdate();
            }

            // 2. Atualizar o plano do usuário
            String updateSql = "UPDATE users SET plan = ? WHERE id = ?";
            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

                updateStmt.setString(1, payment.getPlan());
                updateStmt.setInt(2, payment.getUserId());

                updateStmt.executeUpdate();
            }

            // Confirma as duas operações atomicamente
            conn.commit();

        } // Conexão é fechada automaticamente (try-with-resources)
    }
}