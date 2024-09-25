package cz.engeto.ja2024.GenesisResources_RegistrationSystem.exceptions;

public class UserRepositoryException extends Exception {

    public UserRepositoryException(String message) {
        super(message);
    }

    public UserRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
