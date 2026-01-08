package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    // ADICIONAMOS OS PARAMETROS AQUI NO FINAL DA STRING (?allowPublicKeyRetrieval=true&useSSL=false)
    private static final String URL = "jdbc:mysql://localhost:3306/sys?allowPublicKeyRetrieval=true&useSSL=false";

    private static final String USUARIO = "root";
    private static final String SENHA = "sua_senha_aqui"; // Sua senha que definimos

    public Connection recuperarConexao() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar no banco: " + e.getMessage());
        }
    }
}