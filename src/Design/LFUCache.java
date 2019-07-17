package Design;

import java.util.HashMap;
import java.util.LinkedHashSet;

public class LFUCache {
    /*
     *@Author : Sahil Khurana
     * Date : 17 July 2019
     *
     * Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following
     * operations: get and put.
     *
     * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
     * put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity,
     * it should invalidate the least frequently used item before inserting a new item. For the purpose of this
     * problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently
     * used key would be evicted.
     *
     * Follow up:
     * Could you do both operations in O(1) time complexity?
     *
     * Example:
     *
     * LFUCache cache = new LFUCache( 2 /* capacity * /);
     *
     * cache.put(1,1);
     * cache.put(2,2);
     * cache.get(1);       // returns 1
     * cache.put(3,3);    // evicts key 2
     * cache.get(2);       // returns -1 (not found)
     * cache.get(3);       // returns 3.
     * cache.put(4,4);    // evicts key 1.
     * cache.get(1);       // returns -1 (not found)
     * cache.get(3);       // returns 3
     * cache.get(4);       // returns 4
     *
     * Solution :
     * 1. Take 3 hashmaps
     *      1 for keys,values pair.
     *      2 for keys, and its frquency pair.
     *      3 frequency and linkedhashset of the list of keys associated with it.
     *
     * 2.
     *
     * References : https://leetcode.com/problems/lfu-cache/
     */

    HashMap<Integer, Integer> valuesCache;// cache Key and Value
    HashMap<Integer, Integer> keyFrequencyCache;// Key and counters
    HashMap<Integer, LinkedHashSet<Integer>> frequencyKeyListCache;// Counter and item list
    int capacity; // Маx capacity
    int min = -1; // Track current min

    public LFUCache(int capacity) {
        this.capacity = capacity;
        valuesCache = new HashMap<>();
        keyFrequencyCache = new HashMap<>();
        frequencyKeyListCache = new HashMap<>();
        frequencyKeyListCache.put(1, new LinkedHashSet<>());
    }

    public int get(int key) {
        if (!valuesCache.containsKey(key)) {
            return -1;
        }
        // Get the count from countsCache map
        int freq = keyFrequencyCache.get(key);
        // increase the counter
        keyFrequencyCache.put(key, freq + 1);
        // remove the element from the counter to linkedhashset
        frequencyKeyListCache.get(freq).remove(key);

        // when current min does not have any data, next one would be the min
        if (freq == min && frequencyKeyListCache.get(freq).size() == 0) {
            min++;
        }
        if (!frequencyKeyListCache.containsKey(freq + 1)) {
            frequencyKeyListCache.put(freq + 1, new LinkedHashSet<>());
        }
        frequencyKeyListCache.get(freq + 1).add(key);

        return valuesCache.get(key);
    }

    public void put(int key, int value) {
        if (capacity <= 0) {
            return;
        }
        // If key does exist, we are returning from here
        if (valuesCache.containsKey(key)) {
            valuesCache.put(key, value); // Update the value if needed
            get(key);
            return;
        }
        if (valuesCache.size() >= capacity) {
            int entryToRemove = frequencyKeyListCache.get(min).iterator().next();
            frequencyKeyListCache.get(min).remove(entryToRemove);
            valuesCache.remove(entryToRemove);
            keyFrequencyCache.remove(entryToRemove);
        }
        // If the key is new, insert the value and current min should be 1 of course
        valuesCache.put(key, value);
        keyFrequencyCache.put(key, 1);
        min = 1; // As this is new key, it can occur only once here
        frequencyKeyListCache.get(1).add(key);
    }

    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(3);
        lfuCache.put(1, 1);
        lfuCache.put(2, 2);
        lfuCache.put(3, 3);
        System.out.println("3 present ? : " + lfuCache.get(3));
        lfuCache.put(1, 1);
        lfuCache.put(3, 3);
        lfuCache.put(4, 4);
        System.out.println("2 present ? : " + lfuCache.get(2));
        lfuCache.get(4);
        lfuCache.get(4);
        lfuCache.put(5, 5);
        System.out.println("4 present ? : " + lfuCache.get(4));
        System.out.println("1 present ? : " + lfuCache.get(1));

    }
}
