package vn.com.leetcode.heap_priority_queue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Twitter {

    private Map<Integer, Set<Integer>> followers = new HashMap<>();
    private Map<Integer, List<Tweet>> tweets = new HashMap<>();
    private int time = 0;
    static class Tweet {
        int id;
        int time;
        public Tweet(int id, int time) {
            this.id = id;
            this.time = time;
        }
    }
    public Twitter() {

    }

    public void postTweet(int userId, int tweetId) {
        List<Tweet> tweetList = tweets.get(userId);
        Tweet tweet = new Tweet(tweetId, time++);
        if (tweetList == null) {
            tweetList = new ArrayList<>();
        }
        tweetList.add(tweet);
        tweets.put(userId, tweetList);
    }

    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Tweet> priorityQueue = new PriorityQueue<>((a,b) -> b.time - a.time);

        Set<Integer> followeeList = followers.getOrDefault(userId, new HashSet<>());
        followeeList.add(userId);

        for (int followeeId : followeeList) {
            List<Tweet> tweetList = tweets.get(followeeId);
            if (tweetList != null) {
                priorityQueue.addAll(tweetList);
            }
        }

        List<Integer> result = new ArrayList<>();
        int count = 0;
        while (!priorityQueue.isEmpty() && count < 10) {
            result.add(priorityQueue.poll().id);
            count++;
        }
        return result;
    }

    public void follow(int followerId, int followeeId) {
        Set<Integer> followeeList = followers.get(followerId);
        if (followeeList == null) {
            followeeList = new HashSet<>();
        }
        followeeList.add(followeeId);
        followers.put(followerId, followeeList);
    }

    public void unfollow(int followerId, int followeeId) {
        Set<Integer> followeeList = followers.get(followerId);
        followeeList.remove(followeeId);
        followers.put(followerId, followeeList);
    }

}
