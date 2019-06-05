package Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RandomizedSet {
    /*
     * @Author : Sahil Khurana
     * Date : 03 June 2019
     *
     * Design a data structure that supports all following operations in average O(1) time.
     *
     * insert(val): Inserts an item val to the set if not already present.
     * remove(val): Removes an item val from the set if present.
     * getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
     * Example:
     *
     * // Init an empty set.
     * RandomizedSet randomSet = new RandomizedSet();
     *
     * // Inserts 1 to the set. Returns true as 1 was inserted successfully.
     * randomSet.insert(1);
     *
     * // Returns false as 2 does not exist in the set.
     * randomSet.remove(2);
     *
     * // Inserts 2 to the set, returns true. Set now contains [1,2].
     * randomSet.insert(2);
     *
     * // getRandom should return either 1 or 2 randomly.
     * randomSet.getRandom();
     *
     * // Removes 1 from the set, returns true. Set now contains [2].
     * randomSet.remove(1);
     *
     * // 2 was already in the set, so return false.
     * randomSet.insert(2);
     *
     * // Since 2 is the only number in the set, getRandom always return 2.
     * randomSet.getRandom();
     *
     *
     * References :
     * https://leetcode.com/problems/insert-delete-getrandom-o1/
     *
     *
     * Solution :
     * 1. Take an arrayList and HashMap<value, and its index in arrayList>
     * 2. while inserting, update in hashmap its index to last element, if not already present (check in map)
     * 3. while removing replace last element with the current elment to remove's  index
     * 4. Random can be obtained by using random class
     */

    /**
     * Initialize your data structure here.
     */

    ArrayList<Integer> list;
    HashMap<Integer, Integer> mapTrackIndex;
    Random rand;

    public RandomizedSet() {
        mapTrackIndex = new HashMap<>();
        list = new ArrayList<>();
        rand=new Random();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        boolean containsKeyAlready = mapTrackIndex.containsKey(val);
        if (containsKeyAlready)
            return false;
        list.add(val);
        mapTrackIndex.put(val, list.size() - 1);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        boolean containKeyAlready = mapTrackIndex.containsKey(val);
        if (!containKeyAlready) return false;
        //if it is not last one in the list
        if (mapTrackIndex.get(val) != list.size() - 1) {
            list.set(mapTrackIndex.get(val), list.get(list.size() - 1));    //swap last element with curr position
            mapTrackIndex.put(list.get(list.size() - 1), mapTrackIndex.get(val));
        }
        mapTrackIndex.remove(val);
        list.remove(list.size() - 1);
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }

}
