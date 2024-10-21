package twitter;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.time.Instant;

import org.junit.Test;

public class SocialNetworkTest {

    // Verifies that assertions are enabled
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    // Test 1: guessFollowsGraph with an empty list of tweets
    @Test
    public void testGuessFollowsGraphEmpty() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(new ArrayList<>());
        assertTrue("expected empty graph", followsGraph.isEmpty());
    }

    // Test 2: guessFollowsGraph with no mentions in tweets
    @Test
    public void testNoMentionGuessFollowGraph() {
        Tweet tweet1 = new Tweet(1, "Bilal", "Who are you?", Instant.now());
        Tweet tweet2 = new Tweet(2, "Moiz", "What is happening right now?", Instant.now());
        
        List<Tweet> tweets = new ArrayList<>();
        tweets.add(tweet1);
        tweets.add(tweet2);
        
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);
        assertTrue("expected empty graph", followsGraph.isEmpty());
    }

    // Test 3: guessFollowsGraph with one mention
    @Test
    public void testGuessFollowsGraphSingleMention() {
        Tweet tweet1 = new Tweet(1, "Bilal", "Hello @Moiz", Instant.now());

        List<Tweet> tweets = new ArrayList<>();
        tweets.add(tweet1);
        
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);
        
        assertEquals("expected one follow entry", 1, followsGraph.size());
        assertTrue("Bilal should follow Moiz", followsGraph.get("Bilal").contains("Moiz"));
    }

    // Test 4: guessFollowsGraph with multiple mentions in one tweet
    @Test
    public void testGuessFollowsGraphMultipleMentions() {
        Tweet tweet1 = new Tweet(1, "Shajee", "Hey @Bilal @Moiz @Abdullah", Instant.now());

        List<Tweet> tweets = new ArrayList<>();
        tweets.add(tweet1);
        
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);
        
        assertEquals("expected one follow entry", 1, followsGraph.size());
        Set<String> followedUsers = followsGraph.get("Shajee");
        assertTrue("Shajee should follow Bilal", followedUsers.contains("Bilal"));
        assertTrue("Shajee should follow Moiz", followedUsers.contains("Moiz"));
        assertTrue("Shajee should follow Abdullah", followedUsers.contains("Abdullah"));
    }

    // Test 5: guessFollowsGraph with multiple tweets by the same author
    @Test
    public void testGuessFollowsGraphMultipleTweetsSameAuthor() {
        Tweet tweet1 = new Tweet(1, "Ali", "Hey @Bilal", Instant.now());
        Tweet tweet2 = new Tweet(2, "Ali", "Hi @Abdullah", Instant.now());

        List<Tweet> tweets = new ArrayList<>();
        tweets.add(tweet1);
        tweets.add(tweet2);
        
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);
        
        assertEquals("expected one follow entry", 1, followsGraph.size());
        Set<String> followedUsers = followsGraph.get("Ali");
        assertTrue("Ali should follow Bilal", followedUsers.contains("Bilal"));
        assertTrue("Ali should follow Abdullah", followedUsers.contains("Abdullah"));
    }

    // Test 6: influencers with an empty followsGraph
    @Test
    public void testInfluencersEmpty() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        assertTrue("expected empty list", influencers.isEmpty());
    }

    // Test 7: influencers with one user and no followers
    @Test
    public void testInfluencersSingleUserNoFollowers() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        followsGraph.put("Bilal", new HashSet<>());
        
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        assertTrue("expected empty list", influencers.isEmpty());
    }

 // Test 8: influencers with one user having followers
    @Test
    public void testInfluencersSingleInfluencer() {
        // Create the followsGraph
        Map<String, Set<String>> followsGraph = new HashMap<>();
        
        // Define who follows whom
        Set<String> MoizFollows = new HashSet<>();
        MoizFollows.add("Bilal");
        
        Set<String> AbdullahFollows = new HashSet<>();
        AbdullahFollows.add("Bilal");

        // Populate the followsGraph
        followsGraph.put("Moiz", MoizFollows);        // Moiz follows Bilal
        followsGraph.put("Abdullah", AbdullahFollows); // Abdullah follows Bilal
        
        // Call the influencers method
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        // Check the results
        assertEquals("expected one influencer", 1, influencers.size());
        assertEquals("Bilal should be the influencer", "Bilal", influencers.get(0));
    }


 // Test 9: influencers with multiple users and varying followers
    @Test
    public void testInfluencersMultipleUsers() {
        // Create the followsGraph
        Map<String, Set<String>> followsGraph = new HashMap<>();
        
        // Define who follows whom
        Set<String> MoizFollows = new HashSet<>();
        MoizFollows.add("Bilal");   // Moiz follows Bilal
        MoizFollows.add("Abdullah"); // Moiz also follows Abdullah
        
        Set<String> AbdullahFollows = new HashSet<>();
        AbdullahFollows.add("Bilal");  // Abdullah follows Bilal

        Set<String> BilalFollows = new HashSet<>();
        BilalFollows.add("Abdullah");  // Bilal follows Abdullah

        // Populate the followsGraph
        followsGraph.put("Moiz", MoizFollows);          // Moiz follows Bilal and Abdullah
        followsGraph.put("Abdullah", AbdullahFollows);  // Abdullah follows Bilal
        followsGraph.put("Bilal", BilalFollows);        // Bilal follows Abdullah
        
        // Call the influencers method
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        // Check the results
        assertEquals("expected three influencers", 2, influencers.size());
        assertEquals("Bilal should be the top influencer", "Bilal", influencers.get(0));
        assertTrue("Abdullah should be one of the influencers", influencers.contains("Abdullah"));
        assertFalse("Moiz shouldn't be one of the influencers", influencers.contains("Moiz"));
    }


    // Test 10: influencers with users having equal number of followers
    @Test
    public void testInfluencersEqualFollowers() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        
        Set<String> bilalFollowers = new HashSet<>();
        bilalFollowers.add("Moiz");

        Set<String> moizFollowers = new HashSet<>();
        moizFollowers.add("Bilal");

        followsGraph.put("Bilal", bilalFollowers);
        followsGraph.put("Moiz", moizFollowers);
        
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        assertEquals("expected two influencers", 2, influencers.size());
        assertTrue("Bilal should be one of the influencers", influencers.contains("Bilal"));
        assertTrue("Moiz should be one of the influencers", influencers.contains("Moiz"));
    }
}
