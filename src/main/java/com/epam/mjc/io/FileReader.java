package com.epam.mjc.io;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FileReader {

    public Profile getDataFromFile(File file) {
        Map<String, String> profileData = readProfileData(file);
        return createProfileFromData(profileData);
    }

    private Map<String, String> readProfileData(File file) {
        Map<String, String> profileData = new HashMap<>();
        try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
            String line;
            while ((line = dis.readLine()) != null) {
                String[] keyValue = line.split(":");
                if (keyValue.length == 2) {
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();
                    profileData.put(key, value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return profileData;
    }

    private Profile createProfileFromData(Map<String, String> profileData) {
        String name = profileData.get("Name");
        Integer age = Integer.parseInt(profileData.get("Age"));
        String email = profileData.get("Email");
        Long phone = Long.parseLong(profileData.get("Phone"));
        return new Profile(name, age, email, phone);
    }
}
