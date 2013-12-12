/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package techathon.service.impl;


import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import techathon.core.Application;
import techathon.core.text.sentiment.Classification;
import techathon.service.SentimentAnalyzerService;

/**
 *
 * @author saikatsakura
 */
@Service
public class SentimentAnalyzerServiceImpl implements SentimentAnalyzerService {

    public Classification classify(String text) {
        Classification posC = Application.getInstance().getClassifier(Application.Classifiers.POSITIVE).classify(text);
        Classification negC = Application.getInstance().getClassifier(Application.Classifiers.NEGATIVE).classify(text);
        System.out.println("pos " + posC);
        System.out.println("neg " + negC);
        //int compare = Double.compare(posC.getConfidence(), negC.getConfidence());
        double pConf = posC.getConfidence();
        double nConf = negC.getConfidence();
        BigDecimal bdPosConf = BigDecimal.valueOf(pConf);
        BigDecimal bdNegConf = BigDecimal.valueOf(nConf);
        float flPosConf = Float.parseFloat(bdPosConf.toString().substring(0,3));
        float flNegConf = Float.parseFloat(bdNegConf.toString().substring(0, 3));
        
        if (flPosConf > flNegConf) {
            return posC;
        } else if (flPosConf < flNegConf) {
            return negC;
        } else {
            return new Classification("neutral", 0.0);
        }
        
    }

    public Map<String, Classification> classify(Collection<String> textCollection) {
        Map<String, Classification> classificationMap = new LinkedHashMap<String, Classification>();
        for (String text : textCollection) {
            final Classification classification = classify(text);
            classificationMap.put(text, classification);
        }
        return classificationMap;
    }
    
}
