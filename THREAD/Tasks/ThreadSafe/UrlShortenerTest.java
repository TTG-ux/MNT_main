package THREAD.Tasks.ThreadSafe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UrlShortenerTest {
    
    private UrlShortener shortener;
    
    @BeforeEach
    void setUp() {
        shortener = new UrlShortener();
    }
    
    @Test
    void testEncodeDecode() {
        String longUrl = "https://www.example.com";
        String tinyUrl = shortener.encode(longUrl);
        
        assertNotNull(tinyUrl);
        assertTrue(tinyUrl.startsWith("http://tiny.url/"));
        assertEquals(21, tinyUrl.length());
        
        String decoded = shortener.decode(tinyUrl);
        assertEquals(longUrl, decoded);
    }
    
    @Test
    void testSameUrlSameHash() {
        String url = "https://www.google.com";
        String tiny1 = shortener.encode(url);
        String tiny2 = shortener.encode(url);
        
        assertEquals(tiny1, tiny2);
    }
    
    @Test
    void testDifferentUrlsDifferentHashes() {
        String url1 = "https://www.example.com";
        String url2 = "https://www.google.com";
        
        String tiny1 = shortener.encode(url1);
        String tiny2 = shortener.encode(url2);
        
        assertNotEquals(tiny1, tiny2);
    }
    
    @Test
    void testDecodeInvalidUrl() {
        assertNull(shortener.decode("http://tiny.url/invalid"));
        assertNull(shortener.decode(null));
        assertNull(shortener.decode("wrongformat"));
    }
    
    @Test
    void testThreadSafety() throws InterruptedException {
        String url = "https://www.test.com";
        int threadsCount = 10;
        Thread[] threads = new Thread[threadsCount];
        String[] results = new String[threadsCount];
        
        for (int i = 0; i < threadsCount; i++) {
            final int index = i;
            threads[i] = new Thread(() -> {
                results[index] = shortener.encode(url);
            });
            threads[i].start();
        }
        
        for (Thread thread : threads) {
            thread.join();
        }
        
        // Все потоки должны вернуть одинаковый результат
        String first = results[0];
        for (String result : results) {
            assertEquals(first, result);
        }
    }
    
    @Test
    void testCollisionHandling() {
        // Создаем два URL которые могут иметь одинаковый хеш
        String url1 = "https://www.example.com/path1";
        String url2 = "https://www.example.com/path2";
        
        String tiny1 = shortener.encode(url1);
        String tiny2 = shortener.encode(url2);
        
        assertNotEquals(tiny1, tiny2);
        assertEquals(url1, shortener.decode(tiny1));
        assertEquals(url2, shortener.decode(tiny2));
    }
}
