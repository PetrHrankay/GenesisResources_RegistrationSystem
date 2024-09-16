package cz.engeto.ja2024.GenesisResources_RegistrationSystem.databaseconfiguration;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DatabaseConfiguration {
    private static final String URL = "jdbc:mysql://localhost:3306/GenesisResources";
    private static final String USER = "root";
    private static final String PASSWORD = "Teknomafiacrew";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
