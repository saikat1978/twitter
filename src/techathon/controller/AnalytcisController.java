/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package techathon.controller;

import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import techathon.core.CacheManager;
import techathon.service.TwitterService;

/**
 *
 * @author saikatsakura
 */
@Controller
@RequestMapping(value="/analytics")
public class AnalytcisController {
    
    @Autowired
    private TwitterService twitterService;
    
    @RequestMapping
    public String index() {
        return "index";
    }
    
    @RequestMapping(value="/search", method= RequestMethod.POST)
    public String search(@RequestParam String text, Model model, HttpServletRequest request) {
        List<String> tweets = twitterService.search(text);
        model.addAttribute("tweets", tweets);
        String cacheKey = UUID.randomUUID().toString();
        model.addAttribute(CacheManager.KEY, cacheKey);
        CacheManager.getManager().put(cacheKey, tweets);
        return "analytics/search";
    }
    
    @RequestMapping(value="/search", method= RequestMethod.GET)
    public String search() {
        
        return "analytics/search";
    }
    
}
