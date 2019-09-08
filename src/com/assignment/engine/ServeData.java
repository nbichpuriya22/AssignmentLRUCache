package com.assignment.engine;

import com.assignment.engine.cachepolicy.LRUCache;
import com.assignment.engine.dba.DiscMemory;

/**
 * Class to serve response to the client using LRUCache and Disc.
 */
public class ServeData {

    // LRUCache with max capacity
    private static LRUCache lruCache = new LRUCache(5);

    /**
     * Method to serve response to client based on given key.
     *
     * @param requestedUserId: int
     * @return String
     */
    public static String response(int requestedUserId) {
        String data = lruCache.getData(requestedUserId);
        if (data == null) {
            data = DiscMemory.getData(requestedUserId);
            lruCache.putData(requestedUserId, data);
        }
        return data;
    }

    /**
     * If data is updated by client so remove old data
     * from LRUCache and update the data on Disc.
     *
     * @param userId:      int
     * @param updatedData: String
     */
    public static void updateData(int userId, String updatedData) {
        lruCache.removeUpdatedData(userId);
        DiscMemory.updateData(userId, updatedData);
    }
}
