package cz.engeto.ja2024.GenesisResources_RegistrationSystem.service;

import cz.engeto.ja2024.GenesisResources_RegistrationSystem.exceptions.FileManagerException;
import cz.engeto.ja2024.GenesisResources_RegistrationSystem.exceptions.UserRepositoryException;
import cz.engeto.ja2024.GenesisResources_RegistrationSystem.repository.UserRepository;
import cz.engeto.ja2024.GenesisResources_RegistrationSystem.utils.FileManager;
import cz.engeto.ja2024.GenesisResources_RegistrationSystem.utils.Settings;
import cz.engeto.ja2024.GenesisResources_RegistrationSystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(@Autowired final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public String assignPersonIdToUser() throws UserRepositoryException, FileManagerException {
        List<String> usedPersonIds;
        List<String> allPersonIdsFromFile;

        usedPersonIds = userRepository.getUsedPersonIdFromDatabase();
        allPersonIdsFromFile = FileManager.loadPersonIdFromFile(Settings.PERSONIDFILENAME);

        for (String personIdFromFile : allPersonIdsFromFile) {
            if (!usedPersonIds.contains(personIdFromFile)) {
                return personIdFromFile;
            }
        }
        throw new FileManagerException("No available personId left in the file.");
    }

    public void createUser(User user) throws UserRepositoryException, FileManagerException {
        String availablePersonId = assignPersonIdToUser();
        user.setPersonId(availablePersonId);
        user.setUuid(generateUUID());
        userRepository.createUser(user);
    }

    public User getUserById(long userId) throws UserRepositoryException {
        return userRepository.getUserById(userId);
    }

    public User getUserByIdWithDetails(long userId) throws UserRepositoryException {
        return userRepository.getUserByIdWithDetails(userId);
    }

    public List<User> getAllUsers() throws UserRepositoryException {
        return userRepository.getAllUsers();
    }

    public List<User> getAllUsersWithDetails() throws UserRepositoryException {
        return userRepository.getAllUsersWithDetails();
    }

    public void updateUser(User user) throws UserRepositoryException {
        userRepository.updateUser(user);
    }

    public void deleteUser(long userId) throws UserRepositoryException {
        userRepository.deleteUser(userId);
    }
}
