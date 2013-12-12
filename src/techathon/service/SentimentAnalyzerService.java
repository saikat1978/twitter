/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package techathon.service;



import techathon.core.text.sentiment.Classification;

import java.util.Collection;
import java.util.Map;

/**
 *
 * @author saikatsakura
 */

public interface SentimentAnalyzerService {
    
    public Classification classify(String text);
    public Map<String, Classification> classify(Collection<String> textCollection);
    
}
