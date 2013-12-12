/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package techathon.controller;


import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import techathon.core.CacheManager;
import techathon.core.text.sentiment.Classification;
import techathon.service.SentimentAnalyzerService;

/**
 *
 * @author saikatsakura
 */

@Controller
@RequestMapping(value = "/sentiment")
public class SentimentController {
    
    @Autowired
    private SentimentAnalyzerService service;
    
    @RequestMapping(value = "/analyze", method= RequestMethod.GET)
    public String analyze(String q, HttpServletRequest request) {
        String key = request.getParameter("q");
        List<String> tweets = (List<String>) CacheManager.getManager().get(key);
        request.setAttribute("sentiments", service.classify(tweets));
        return "sentiment/analyze";
    }
    
    @RequestMapping(value = "/analyze", method= RequestMethod.POST)
    public String analyzeText(@RequestParam String text, HttpServletRequest request) {
        Classification classification = service.classify(text);
        request.setAttribute("classification", classification);
        request.setAttribute("text", text);
        return "sentiment/textResult";
    }
}
