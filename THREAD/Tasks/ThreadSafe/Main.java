package THREAD.Tasks.ThreadSafe;

public class Main {
    public static void main(String[] args) {
        // Basic version
        UrlShortener shortener = new UrlShortener();
        
        String longUrl = "https://www.example.com/very/long/path/to/resource";
        String tinyUrl = shortener.encode(longUrl);
        
        System.out.println("Original: " + longUrl);
        System.out.println("Shortened: " + tinyUrl);
        System.out.println("Decoded: " + shortener.decode(tinyUrl));
        
        // With pool version
        System.out.println("\n--- With Pool ---");
        UrlShortenerWithPool poolShortener = new UrlShortenerWithPool(10);
        
        for (int i = 0; i < 5; i++) {
            String url = "https://www.test" + i + ".com";
            String tiny = poolShortener.encode(url);
            System.out.println("Encoded " + url + " -> " + tiny);
        }
        
        System.out.println("Remaining links: " + poolShortener.getRemainingLinks());
    }
}