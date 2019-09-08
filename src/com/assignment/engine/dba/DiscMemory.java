package com.assignment.engine.dba;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to represent a Dummy Disc
 */
public class DiscMemory {

    private static Map<Integer, String> data = new HashMap<>();

    /*
     * load some dummy data.
     */
    static {
        for (int i = 0; i < 10; i++) {
            data.put(i, "/user/location/profile_" + System.currentTimeMillis() + i + ".json");
        }
        // showData();
    }

    /**
     * Getter to get specific data from Disc
     *
     * @param key: String
     * @return Data as Object
     */
    public static String getData(int key) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return data.getOrDefault(key, null);
    }

    /**
     * Print all the Disc data on console.
     */
    public static void showData() {
        System.out.println("Disc Data");
        for (Map.Entry<Integer, String> metadata : data.entrySet()) {
            System.out.println("\t" + metadata.getKey() + " -> " + metadata.getValue());
        }
        System.out.println("-".repeat(60));
    }

    /**
     * Update the stored data for specific id.
     *
     * @param userId:      int
     * @param updatedData: String
     */
    public static void updateData(int userId, String updatedData) {
        data.put(userId, updatedData);
    }
}
