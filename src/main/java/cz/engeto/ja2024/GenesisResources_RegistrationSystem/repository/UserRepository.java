package cz.engeto.ja2024.GenesisResources_RegistrationSystem.repository;

import cz.engeto.ja2024.GenesisResources_RegistrationSystem.databaseconfiguration.DatabaseConfiguration;
import cz.engeto.ja2024.GenesisResources_RegistrationSystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.UUID;

@Repository
public class UserRepository {

    private final DatabaseConfiguration databaseConfiguration;

    public UserRepository(@Autowired final DatabaseConfiguration databaseConfiguration) {
        this.databaseConfiguration = databaseConfiguration;
    }

    public String generateUUID() {
        return UUID.randomUUID().toString();
    }



}



