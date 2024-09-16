package cz.engeto.ja2024.GenesisResources_RegistrationSystem.service;

import cz.engeto.ja2024.GenesisResources_RegistrationSystem.databaseconfiguration.DatabaseConfiguration;
import cz.engeto.ja2024.GenesisResources_RegistrationSystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Service
public class UserService {

    private final DatabaseConfiguration databaseConfiguration;

    public UserService(@Autowired final DatabaseConfiguration databaseConfiguration) {
            this.databaseConfiguration = databaseConfiguration;
        }

    public String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public void createUser(User user) {
        String query = "INSERT INTO users (name, surname, person_id, uuid) VALUES (?, ?, ?, ?)";

        try(Connection connection = databaseConfiguration.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1,user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getPersonId());
            statement.setString(4, generateUUID());

            statement.executeUpdate();
            System.out.println("The user has been successfully saved to the database.");

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
