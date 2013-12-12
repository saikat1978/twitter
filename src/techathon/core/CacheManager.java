/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package techathon.core;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author saikatsakura
 */
public class CacheManager {
    private static Map<String, Object> cache;
    private static CacheManager me;
    
    public static final String KEY = "key";
    static {
        cache = new LinkedHashMap<String, Object>();
    }
    
    private CacheManager () {}
    
    public static synchronized CacheManager getManager() {
        if (me == null) {
            me = new CacheManager();
        }
        return me;
    }
    
    public void put(String key, Object value) {
        cache.put(key, value);
    }
    
    public Object get(String key) {
        return cache.get(key);
    }
    
    
    
}
