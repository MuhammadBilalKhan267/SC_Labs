package twitter;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SocialNetwork {

    /**
     * Guess who might follow whom, from evidence found in tweets.
     * 
     * @param tweets
     *            a list of tweets providing the evidence, not modified by this
     *            method.
     * @return a social network (as defined above) in which Ernie follows Bert
     *         if and only if there is evidence for it in the given list of
     *         tweets.
     */
    public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        Pattern mentionPattern = Pattern.compile("@(\\w+)");

        for (Tweet tweet : tweets) {
            String author = tweet.getAuthor();
            Matcher matcher = mentionPattern.matcher(tweet.getText());


            while (matcher.find()) {
            	// Initialize the author's entry in the graph if necessary
                followsGraph.putIfAbsent(author, new HashSet<>());
                String mentionedUser = matcher.group(1);
                // Avoid self-following
                if (!author.equals(mentionedUser)) {
                    followsGraph.get(author).add(mentionedUser);
                }
            }
        }

        return followsGraph;
    }

    /**
     * Find the people in a social network who have the greatest influence, in
     * the sense that they have the most followers.
     * 
     * @param followsGraph
     *            a social network (as defined above)
     * @return a list of all distinct Twitter usernames in followsGraph, in
     *         descending order of follower count.
     */
    public static List<String> influencers(Map<String, Set<String>> followsGraph) {
        Map<String, Integer> followerCount = new HashMap<>();

        // Count the followers for each user
        for (Set<String> followedSet : followsGraph.values()) {
            for (String followed : followedSet) {
                followerCount.put(followed, followerCount.getOrDefault(followed, 0) + 1);
            }
        }

        // Create a list of influencers sorted by the number of followers
        List<String> influencers = new ArrayList<>(followerCount.keySet());
        influencers.sort((a, b) -> {
            int cmp = followerCount.get(b).compareTo(followerCount.get(a));
            // If follower count is equal, break ties by custom rule if needed
            if (cmp == 0) {
                return b.compareTo(a);
            }
            return cmp;
        });


        return influencers;
    }

}
