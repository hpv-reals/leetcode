package vn.com.leetcode.binary_search;

import java.util.HashMap;
import java.util.Map;

public class TimeMap {

    /**
     * Level: Medium
     * Start: 13:38 23/06/2026
     * End: 13:47 23/06/2026
     */
    Map<String, Map<Integer, String>> map = new HashMap<>();

    public TimeMap() {

    }

    public void set(String key, String value, int timestamp) {
        Map<Integer, String> innerMap = map.get(key);
        if (innerMap == null) {
            innerMap = new HashMap<>();
            innerMap.put(timestamp, value);
        }
        innerMap.put(timestamp, value);
        map.put(key, innerMap);
    }

    public String get(String key, int timestamp) {
        if (timestamp == 0) {
            return "";
        }
        Map<Integer, String> innerMap = map.get(key);
        if (innerMap == null) {
            return get(key, --timestamp);
        }
        while (timestamp >= 0) {
            String value = innerMap.get(timestamp--);
            if (value != null) {
                return value;
            }
        }
        return "";
    }


    public static void main(String[] args) {
        TimeMap timeMap = new TimeMap();
        timeMap.set("alice", "happy", 1);  // store the key "alice" and value "happy" along with timestamp = 1.
        timeMap.get("alice", 1);           // return "happy"
        timeMap.get("alice", 2);           // return "happy", there is no value stored for timestamp 2, thus we return the value at timestamp 1.
        timeMap.set("alice", "sad", 3);    // store the key "alice" and value "sad" along with timestamp = 3.
        timeMap.get("alice", 3);           // return "sad"
    }

}
