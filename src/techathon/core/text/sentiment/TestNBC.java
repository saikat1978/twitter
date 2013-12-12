package techathon.core.text.sentiment;

import java.io.FileReader;
import java.io.IOException;

import com.google.gson.JsonObject;
import com.google.gson.JsonStreamParser;

public class TestNBC {

    public static void main(String[] args) {

        String filename = args.length >= 1 ? args[0] : "C:\\TwitterDA\\TwitterDataAnalytics\\owssad.json";

        //initialize the sentiment classifier
        NaiveBayesSentimentClassifier nbsc = new NaiveBayesSentimentClassifier();

        try {
            //read the file, and train each document
            JsonStreamParser parser = new JsonStreamParser(new FileReader(filename));
            JsonObject elem;
            String text;
            while (parser.hasNext()) {
                elem = parser.next().getAsJsonObject();
                text = elem.get("text").getAsString();
                nbsc.trainInstance(text);
            }

            //print out the positive and negative dictionary
            System.out.println("=== Positive Dictionary ===");
            System.out.println(nbsc.printWordOccurs(0, 25));
            System.out.println("=== Negative Dictionary ===");
            System.out.println(nbsc.printWordOccurs(1, 25));

            //now go through and classify each line as positive or negative
//			parser = new JsonStreamParser(new FileReader(filename));
//			while (parser.hasNext()) {
//	            elem = parser.next().getAsJsonObject();
//	            text = elem.get("text").getAsString();
//	            Classification c = nbsc.classify(text);
//	            System.out.println(c + " -> " + text);
//			}
            System.out.println(nbsc.classify("\"@Ra_Bies: Appoint Rahul Gandhi as Prime Minister, Pakistan shall appear to be a smaller threat\""));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
