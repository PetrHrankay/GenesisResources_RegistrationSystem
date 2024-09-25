package cz.engeto.ja2024.GenesisResources_RegistrationSystem.repository;

import cz.engeto.ja2024.GenesisResources_RegistrationSystem.databaseconfiguration.DatabaseConfiguration;
import cz.engeto.ja2024.GenesisResources_RegistrationSystem.exceptions.UserRepositoryException;
import cz.engeto.ja2024.GenesisResources_RegistrationSystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class UserRepository {

    private final DatabaseConfiguration databaseConfiguration;
    Logger logger = Logger.getLogger(getClass().getName());

    public UserRepository(@Autowired final DatabaseConfiguration databaseConfiguration) {
        this.databaseConfiguration = databaseConfiguration;
    }

    public void createUser(User user) throws UserRepositoryException {
        String query = "INSERT INTO users (name, surname, person_id, uuid) VALUES (?, ?, ?, ?)";

        try (Connection connection = databaseConfiguration.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getPersonId());
            statement.setString(4, user.getUuid());

            statement.executeUpdate();
            logger.info("The user has been successfully saved to the database.");

        } catch (SQLException e) {
            throw new UserRepositoryException("Database error while creating user", e);
        }catch (RuntimeException e) {
            throw new UserRepositoryException("Runtime error occurred while creating user", e);
        }
    }

    public List<String> getUsedPersonIdFromDatabase() throws UserRepositoryException {
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
            throw new UserRepositoryException("Database error while loading person_id", e);
        }
        return personIds;
    }

    public User getUserById(long userId) throws UserRepositoryException {
        String query = "SELECT id, name, surname FROM users WHERE id = ?";

        try (Connection connection = databaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getString("surname"));
                } else {
                    throw new UserRepositoryException("User with id: " + userId + " doesn't exist.");
                }
            }
        } catch (SQLException e) {
            throw new UserRepositoryException("Database error while fetching user by id", e);
        }
    }

    public User getUserByIdWithDetails(long userId) throws UserRepositoryException {
        String query = "SELECT id, name, surname, person_id, uuid FROM users WHERE id = ?";

        try (Connection connection = databaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getString("surname"),
                            resultSet.getString("person_id"),
                            resultSet.getString("uuid"));
                } else {
                    throw new UserRepositoryException("User with id: " + userId + " doesn't exist.");
                }
            }
        } catch (SQLException e) {
            throw new UserRepositoryException("Database error while fetching user details by id", e);
        }
    }

    public List<User> getAllUsers() throws UserRepositoryException {
        String query = "SELECT id, name, surname FROM users";

        List<User> out = new ArrayList<>();

        try (Connection connection = databaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"));
                out.add(user);
            }
        } catch (SQLException e) {
            throw new UserRepositoryException("Database error while fetching all users", e);
        }
        return out;
    }

    public List<User> getAllUsersWithDetails() throws UserRepositoryException {
        String query = "SELECT id, name, surname, person_id, uuid FROM users";

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
                        resultSet.getString("uuid"));
                out.add(user);
            }
        } catch (SQLException e) {
            throw new UserRepositoryException("Database error while fetching all users", e);
        }
        return out;
    }

    public void updateUser(User user) throws UserRepositoryException {
        String query = "UPDATE users SET name = ?, surname = ? WHERE id = ?";

        try (Connection connection = databaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setLong(3, user.getId());

            preparedStatement.executeUpdate();
            logger.info("User has been updated.");

        } catch (SQLException e) {
            throw new UserRepositoryException("Database error while updating user", e);
        }
    }

    public void deleteUser(long userId) throws UserRepositoryException {
        String query = "DELETE FROM users WHERE id = ?";

        try (Connection connection = databaseConfiguration.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, userId);
            preparedStatement.executeUpdate();
            logger.info("User with id: " + userId + " has been deleted");

        } catch (SQLException e) {
            throw new UserRepositoryException("Database error while deleting user with id: " + userId, e);
        }
    }
}