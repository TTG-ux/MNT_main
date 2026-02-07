package THREAD.Tasks.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;

public class UrlShortener {
    
    private static final String BASE_URL = "http://tiny.url/";
    private static final int HASH_LENGTH = 7;
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    
    // Thread-safe storage
    private final ConcurrentHashMap<String, String> urlToHash;
    private final ConcurrentHashMap<String, String> hashToUrl;
    
    public UrlShortener() {
        this.urlToHash = new ConcurrentHashMap<>();
        this.hashToUrl = new ConcurrentHashMap<>();
    }
    
    /**
     * Encodes a long URL to a tiny URL
     */
    public String encode(String longUrl) {
        if (longUrl == null || longUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("URL cannot be null or empty");
        }
        
        // Check if URL already exists
        String existingHash = urlToHash.get(longUrl);
        if (existingHash != null) {
            return BASE_URL + existingHash;
        }
        
        // Generate unique hash
        String hash = generateUniqueHash(longUrl);
        
        // Store mapping atomically
        urlToHash.put(longUrl, hash);
        hashToUrl.put(hash, longUrl);
        
        return BASE_URL + hash;
    }
    
    /**
     * Decodes a tiny URL to the original URL
     */
    public String decode(String tinyUrl) {
        if (tinyUrl == null || !tinyUrl.startsWith(BASE_URL)) {
            return null;
        }
        
        String hash = tinyUrl.substring(BASE_URL.length());
        return hashToUrl.get(hash);
    }
    
    /**
     * Generates a unique hash for the given URL
     */
    private String generateUniqueHash(String url) {
        String hash = generateHash(url);
        
        // Handle collisions using open addressing
        int attempt = 0;
        while (hashToUrl.containsKey(hash)) {
            // Check if it's the same URL (shouldn't happen due to urlToHash check)
            String existingUrl = hashToUrl.get(hash);
            if (existingUrl.equals(url)) {
                return hash;
            }
            
            // Generate new hash by appending attempt number
            hash = generateHash(url + attempt);
            attempt++;
        }
        
        return hash;
    }
    
    /**
     * Simple hash generation using string hash code
     */
    private String generateHash(String input) {
        long hashValue = Math.abs(input.hashCode());
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < HASH_LENGTH; i++) {
            sb.append(CHARACTERS.charAt((int)(hashValue % CHARACTERS.length())));
            hashValue = hashValue / CHARACTERS.length();
        }
        
        return sb.toString();
    }
}
