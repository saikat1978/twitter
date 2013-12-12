/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package techathon.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import techathon.core.TwitterSession;
import techathon.service.AbstractTwitterService;
import techathon.service.TwitterService;
import twitter4j.GeoLocation;
import twitter4j.Location;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 *
 * @author saikatsakura
 */
@Service
public class TwitterServiceImpl extends AbstractTwitterService implements TwitterService {

    private TwitterSession session;
    private Twitter twitter;
    
    public TwitterServiceImpl() throws TwitterException {
        session = getAuthenticatedTwitterSession();
        twitter = session.getTwitterSession();
    }
    public List<String> search(String text) {
        List<String> tweets = new LinkedList<String>();
        Query query = new Query(text);
        query.setCount(100);
        try {
            TwitterSession session = getAuthenticatedTwitterSession();
            QueryResult queryResult = session.getTwitterSession().search(query);
            if (queryResult.hasNext()) {
                while (queryResult.hasNext()) {
                    for (Status s : queryResult.getTweets()) {
                        tweets.add(s.getText());
                    }

                    queryResult = session.getTwitterSession().search(queryResult.nextQuery());
                }
            } else {
                for (Status s : queryResult.getTweets()) {
                    tweets.add(s.getText());
                }
            }
        } catch (TwitterException ex) {
            Logger.getLogger(TwitterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tweets;
    }

    public List<String> getHomeTimeline() {
        List<String> tweets = new LinkedList<String>();
        try {
            TwitterSession session = getAuthenticatedTwitterSession();
            Twitter twitter = session.getTwitterSession();
            for (int i = 1; i <= 1; i++) {
                final Paging paging = new Paging(i, 100);
                ResponseList<Status> statuses = twitter.getHomeTimeline(paging);
                for (Status s : statuses) {
                    tweets.add(s.getText());
                }
            }
            
            
        
        } catch (TwitterException ex) {
            Logger.getLogger(TwitterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return tweets;
        }
    }

    public Map<String, Trend[]> getLocalTrends(GeoLocation geo) {
        Map<String, Trend[]> returnList = new HashMap<String, Trend[]>();
        try {
            ResponseList<Location> statuses = twitter.getClosestTrends(geo);
            for (Location l : statuses) {
                Trends trends = twitter.getPlaceTrends(l.getWoeid());
                returnList.put(trends.getLocation().getName(), trends.getTrends());
            }
        } catch (TwitterException ex) {
            Logger.getLogger(TwitterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnList;
    }
}
