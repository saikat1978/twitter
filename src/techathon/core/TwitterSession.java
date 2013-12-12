/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package techathon.core;

import twitter4j.Twitter;
import twitter4j.User;

/**
 *
 * @author saikatsakura
 */
public class TwitterSession {
    
    private Twitter twitterSession;
    private User authenticatedUser;

    /**
     * @return the twitterSession
     */
    public Twitter getTwitterSession() {
        return twitterSession;
    }

    /**
     * @param twitterSession the twitterSession to set
     */
    public void setTwitterSession(Twitter twitterSession) {
        this.twitterSession = twitterSession;
    }

    /**
     * @return the authenticatedUser
     */
    public User getAuthenticatedUser() {
        return authenticatedUser;
    }

    /**
     * @param authenticatedUser the authenticatedUser to set
     */
    public void setAuthenticatedUser(User authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
    }
    
}
