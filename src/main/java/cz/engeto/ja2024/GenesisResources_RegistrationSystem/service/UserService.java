package cz.engeto.ja2024.GenesisResources_RegistrationSystem.service;

import cz.engeto.ja2024.GenesisResources_RegistrationSystem.utils.FileManager;
import cz.engeto.ja2024.GenesisResources_RegistrationSystem.utils.Settings;
import cz.engeto.ja2024.GenesisResources_RegistrationSystem.databaseconfiguration.DatabaseConfiguration;
import cz.engeto.ja2024.GenesisResources_RegistrationSystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

@Service
public class UserService {

    private final DatabaseConfiguration databaseConfiguration;
    Logger logger = Logger.getLogger(getClass().getName());


    public UserService(@Autowired final DatabaseConfiguration databaseConfiguration) {
        this.databaseConfiguration = databaseConfiguration;
    }

    public String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public void createUser(User user) {
        String availablePersonId = assignPersonIdToUser();
        user.setPersonId(availablePersonId);

        String query = "INSERT INTO users (name, surname, person_id, uuid) VALUES (?, ?, ?, ?)";

        try (Connection connection = databaseConfiguration.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getPersonId());
            statement.setString(4, generateUUID());

            statement.executeUpdate();
            logger.info("The user has been successfully saved to the database.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getUsedPersonIdFromDatabase() {
        List<String> personIds = new ArrayList<>();

        String query = "SELECT person_id FROM users";

        try (Connection connection = databaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                String personId = resultSet.getString("person_id");
                personIds.add(personId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while fetching person_id", e);
        }
        return personIds;
    }

    public String assignPersonIdToUser() {
        List<String> usedPersonIds = getUsedPersonIdFromDatabase();
        List<String> allPersonIdsFromFile = FileManager.loadPersonIdFromFile(Settings.PERSONIDFILENAME);

        for (String personIdFromFile : allPersonIdsFromFile) {
            if (!usedPersonIds.contains(personIdFromFile)) {
                return personIdFromFile;
            }
        }
        throw new RuntimeException("No available personId found.");
    }

    public User getUserById(long userId) throws SQLException {
        String query = "SELECT id, name, surname FROM users WHERE id = " + userId;

        try (Connection connection = databaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getString("surname"));
                } else {
                    throw new SQLException("User with id: " + userId + " doesn't exist.");
                }
            }
        }
    }

    public User getUserByIdWithDetails(long userId) throws SQLException {
        String query = "SELECT id, name, surname, person_id, uuid FROM users WHERE id = " + userId;

        try (Connection connection = databaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            resultSet.getString("person_id"),
                            resultSet.getString("uuid"));
                } else {
                    throw new SQLException("User with id: " + userId + " doesn't exist.");
                }
            }
        }
    }

    public List<User> getAllUsers() throws SQLException {
        String query = "SELECT id, name, surname FROM users";

        List<User> out = new ArrayList<>();

        try (Connection connection = databaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname")
                );
                out.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return out;
    }

    public List<User> getAllUsersWithDetails() throws SQLException {
        String query = "SELECT id, name, surname, person_id, uuid  FROM users";

        List<User> out = new ArrayList<>();

        try (Connection connection = databaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("person_id"),
                        resultSet.getString("uuid")
                );
                out.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return out;
    }

    public void updateUser(User user) {
        String query = "UPDATE users SET name = ?, surname = ? WHERE id = ?";

        try (Connection connection = databaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setLong(3, user.getId());

            preparedStatement.executeUpdate();

            logger.info("User has been updated.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(long userId) {
        String query = "DELETE FROM users WHERE id = " + userId;

        try (Connection connection = databaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

