package THREAD.Tasks.ThreadSafe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UrlShortenerWithPoolTest {
    
    private UrlShortenerWithPool shortener;
    
    @BeforeEach
    void setUp() {
        shortener = new UrlShortenerWithPool(5);
    }
    
    @Test
    void testPoolExhaustion() {
        for (int i = 0; i < 5; i++) {
            shortener.encode("https://www.test" + i + ".com");
        }
        
        assertEquals(0, shortener.getRemainingLinks());
        
        assertThrows(RuntimeException.class, () -> {
            shortener.encode("https://www.overflow.com");
        });
    }
    
    @Test
    void testPoolReset() {
        for (int i = 0; i < 5; i++) {
            shortener.encode("https://www.test" + i + ".com");
        }
        
        shortener.resetPool();
        assertEquals(5, shortener.getRemainingLinks());
        
        // Can encode again
        String url = shortener.encode("https://www.new.com");
        assertNotNull(url);
    }
    
    @Test
    void testSequentialAccess() {
        shortener.setUseRandom(false);
        
        String url1 = shortener.encode("https://www.first.com");
        String url2 = shortener.encode("https://www.second.com");
        
        assertNotNull(url1);
        assertNotNull(url2);
        assertNotEquals(url1, url2);
    }
    
    @Test
    void testRandomAccess() {
        shortener.setUseRandom(true);
        
        String url1 = shortener.encode("https://www.first.com");
        String url2 = shortener.encode("https://www.second.com");
        
        assertNotNull(url1);
        assertNotNull(url2);
    }
}
