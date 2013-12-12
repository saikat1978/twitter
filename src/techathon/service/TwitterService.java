/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package techathon.service;

import java.util.List;
import java.util.Map;
import twitter4j.GeoLocation;
import twitter4j.Trend;

/**
 *
 * @author saikatsakura
 */
public interface TwitterService {
    
    public List<String> search(String text);
    public List<String> getHomeTimeline();
    public Map<String, Trend[]> getLocalTrends(GeoLocation geo);
}
