/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package techathon.scratchpad;



import techathon.core.text.sentiment.NaiveBayesSentimentClassifier;
import twitter4j.*;
import twitter4j.Twitter;
import twitter4j.auth.AccessToken;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author saikatsakura
 */
public class NBC {
    
    public  static void main(String[] args) throws TwitterException {
        AccessToken accessToken = new AccessToken("27447997-DI7nEr57tcfJ9lSjgfPssPdOcRZuo6K3bDkL08xNl",
                "hUQsknqfweVQumiNwjj6ZsfiDZbD28q2973sWvJdvalug");

        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer("NOeCA0AeBPDMBrJxFj4BPQ", "AsltzXSiMi6GaEvMnMOe56bqrvMc09RVZrlQa5YG2jI");
        twitter.setOAuthAccessToken(accessToken);

        User user = twitter.verifyCredentials();
        System.out.println(user.getName());
        System.out.println(user.getScreenName());
        Query query = new Query("ows");
        query.setCount(100);
        QueryResult queryResult = twitter.search(query);
        List<String> textList = new LinkedList<String>();
        System.out.println(queryResult.getCount() + " " + queryResult.hasNext());
        NaiveBayesSentimentClassifier nbsc = new NaiveBayesSentimentClassifier();
        
        if (queryResult.hasNext()) {
            while (queryResult.hasNext()) {
                for (Status status : queryResult.getTweets()) {
                    textList.add(status.getText());
                    
                }
                final Query nextQuery = queryResult.nextQuery();
                queryResult = twitter.search(nextQuery);
            }
        } else {
            for (Status status : queryResult.getTweets()) {
                textList.add(status.getText());
            }
                
        }
        System.out.println("going to train instances");
        nbsc.trainInstances(textList);
        System.out.println("=== Positive Dictionary ===");
        System.out.println(nbsc.printWordOccurs(0, 25));
        System.out.println("=== Negative Dictionary ===");
        System.out.println(nbsc.printWordOccurs(1, 25));
        System.out.println(nbsc.classify("i am so sad"));
        
//        for (String text : textList) {
//            final Classification c = nbsc.classify(text);
//            System.out.println(c + " ->>>> " + text);
//        }
    }
    
    
    
}
