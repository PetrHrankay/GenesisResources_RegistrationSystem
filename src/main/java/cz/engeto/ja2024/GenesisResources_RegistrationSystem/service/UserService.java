package cz.engeto.ja2024.GenesisResources_RegistrationSystem.service;

import cz.engeto.ja2024.GenesisResources_RegistrationSystem.repository.UserRepository;
import cz.engeto.ja2024.GenesisResources_RegistrationSystem.utils.FileManager;
import cz.engeto.ja2024.GenesisResources_RegistrationSystem.utils.Settings;
import cz.engeto.ja2024.GenesisResources_RegistrationSystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(@Autowired final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String assignPersonIdToUser() {
        List<String> usedPersonIds = userRepository.getUsedPersonIdFromDatabase();
        List<String> allPersonIdsFromFile = FileManager.loadPersonIdFromFile(Settings.PERSONIDFILENAME);

        for (String personIdFromFile : allPersonIdsFromFile) {
            if (!usedPersonIds.contains(personIdFromFile)) {
                return personIdFromFile;
            }
        }
        throw new RuntimeException("No available personId found.");
    }

    public void createUser(User user) {
        String availablePersonId = assignPersonIdToUser();
        user.setPersonId(availablePersonId);
        userRepository.createUser(user);
    }

    public User getUserById(long userId) throws SQLException {
        return userRepository.getUserById(userId);
    }

    public User getUserByIdWithDetails(long userId) throws SQLException {
        return userRepository.getUserByIdWithDetails(userId);
    }

    public List<User> getAllUsers() throws SQLException {
        return userRepository.getAllUsers();
    }

    public List<User> getAllUsersWithDetails() throws SQLException {
        return userRepository.getAllUsersWithDetails();
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    public void deleteUser(long userId) {
        userRepository.deleteUser(userId);
    }
}
