package THREAD.Tasks.ThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class UrlShortenerWithPool {
    
    private static final String BASE_URL = "http://tiny.url/";
    
    // Thread-safe storage
    private final ConcurrentHashMap<String, String> urlToHash;
    private final ConcurrentHashMap<String, String> hashToUrl;
    
    // Fixed pool of links
    private final List<String> linkPool;
    private final AtomicInteger currentIndex;
    private final ReentrantLock poolLock;
    private boolean useRandom;
    
    public UrlShortenerWithPool(int poolSize) {
        this.urlToHash = new ConcurrentHashMap<>();
        this.hashToUrl = new ConcurrentHashMap<>();
        this.linkPool = new ArrayList<>();
        this.currentIndex = new AtomicInteger(0);
        this.poolLock = new ReentrantLock();
        this.useRandom = true;
        
        generatePool(poolSize);
    }
    
    /**
     * Encodes a long URL to a tiny URL using pool
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
        
        // Get link from pool
        String hash = getNextLinkFromPool();
        if (hash == null) {
            throw new RuntimeException("Link pool exhausted!");
        }
        
        // Store mapping
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
     * Gets next available link from pool
     */
    private String getNextLinkFromPool() {
        poolLock.lock();
        try {
            if (currentIndex.get() >= linkPool.size()) {
                return null;
            }
            
            String link;
            if (useRandom) {
                // Random access
                int randomIndex = (int) (Math.random() * (linkPool.size() - currentIndex.get()));
                link = linkPool.get(randomIndex);
                
                // Swap to avoid reuse
                Collections.swap(linkPool, randomIndex, currentIndex.get());
            } else {
                // Sequential access
                link = linkPool.get(currentIndex.get());
            }
            
            currentIndex.incrementAndGet();
            return link;
        } finally {
            poolLock.unlock();
        }
    }
    
    /**
     * Generates pool of unique links
     */
    private void generatePool(int size) {
        for (int i = 0; i < size; i++) {
            String hash = generateUniqueHash("pool_seed_" + i);
            linkPool.add(hash);
        }
        
        // Shuffle for random access
        Collections.shuffle(linkPool);
    }
    
    /**
     * Generates unique hash (simple implementation)
     */
    private String generateUniqueHash(String input) {
        long hashValue = Math.abs(input.hashCode());
        StringBuilder sb = new StringBuilder();
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        
        for (int i = 0; i < 7; i++) {
            sb.append(chars.charAt((int)(hashValue % chars.length())));
            hashValue = hashValue / chars.length();
        }
        
        return sb.toString();
    }
    
    /**
     * Reset the pool to reuse links
     */
    public void resetPool() {
        poolLock.lock();
        try {
            currentIndex.set(0);
            Collections.shuffle(linkPool);
        } finally {
            poolLock.unlock();
        }
    }
    
    /**
     * Switch between random and sequential access
     */
    public void setUseRandom(boolean useRandom) {
        this.useRandom = useRandom;
    }
    
    /**
     * Get remaining links in pool
     */
    public int getRemainingLinks() {
        return linkPool.size() - currentIndex.get();
    }
}
