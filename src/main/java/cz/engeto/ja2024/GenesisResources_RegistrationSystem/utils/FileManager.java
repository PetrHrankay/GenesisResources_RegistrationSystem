package cz.engeto.ja2024.GenesisResources_RegistrationSystem.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public static List<String> loadPersonIdFromFile(String fileName) {
        List<String> personIds = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                personIds.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error while reading file", e);
        }
        return personIds;
    }
}
