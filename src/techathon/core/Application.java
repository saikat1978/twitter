/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package techathon.core;


import com.google.gson.JsonObject;
import com.google.gson.JsonStreamParser;
import techathon.core.text.sentiment.NaiveBayesSentimentClassifier;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author saikatsakura
 */
public class Application {
    
    private static NaiveBayesSentimentClassifier positiveClassifier;
    private static NaiveBayesSentimentClassifier negativeClassifier;
    
    static {
        positiveClassifier = new NaiveBayesSentimentClassifier();
        negativeClassifier = new NaiveBayesSentimentClassifier();
    }
    
    private Application() {
        initializePositiveClassifier();
        initializeNegativeClassifier();
    }

    private void initializePositiveClassifier() {
        try {
            String seedFile = "C:\\TwitterDA\\TwitterDataAnalytics\\owshappy.json";
            JsonStreamParser parser = new JsonStreamParser(new FileReader(seedFile));
                JsonObject elem;
                String text;
                while (parser.hasNext()) {
                    elem = parser.next().getAsJsonObject();
                    text = elem.get("text").getAsString();
                    positiveClassifier.trainInstance(text);
                }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void initializeNegativeClassifier() {
        try {
            String seedFile = "C:\\TwitterDA\\TwitterDataAnalytics\\owssad.json";
            JsonStreamParser parser = new JsonStreamParser(new FileReader(seedFile));
                JsonObject elem;
                String text;
                while (parser.hasNext()) {
                    elem = parser.next().getAsJsonObject();
                    text = elem.get("text").getAsString();
                    negativeClassifier.trainInstance(text);
                }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static Application me;
    public static synchronized Application getInstance() {
        if (me == null) {
            me = new Application();
        }
        return me;
    }
    
    public static enum Classifiers { POSITIVE, NEGATIVE }
    public NaiveBayesSentimentClassifier getClassifier(Classifiers c) {
        if (c == Classifiers.POSITIVE)
            return positiveClassifier;
        else 
            return negativeClassifier;
    }
    
    
}
