/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package techathon.service;

import techathon.core.TwitterSession;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;

/**
 *
 * @author saikatsakura
 */
public abstract class AbstractTwitterService {
    
    protected TwitterSession getAuthenticatedTwitterSession() throws TwitterException {
        AccessToken accessToken = new AccessToken("27447997-DI7nEr57tcfJ9lSjgfPssPdOcRZuo6K3bDkL08xNl",
                "hUQsknqfweVQumiNwjj6ZsfiDZbD28q2973sWvJdvalug");

        Twitter session = new TwitterFactory().getInstance();
        
        session.setOAuthConsumer("NOeCA0AeBPDMBrJxFj4BPQ", "AsltzXSiMi6GaEvMnMOe56bqrvMc09RVZrlQa5YG2jI");
        session.setOAuthAccessToken(accessToken);
        User user = session.verifyCredentials();
        TwitterSession twitterSession = new TwitterSession();
        twitterSession.setAuthenticatedUser(user);
        twitterSession.setTwitterSession(session);
        return twitterSession;
    }
    
}
