import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * URLParser.java
 * Program to parse and extract individual components of a given URL string.
 * Course: BIT 2203 - Advanced Programming
 * @author Nyevu Chea
 * @registration [Your Registration Number]
 * @date 2026-08-18
 */
public class URLParser {

    public static void main(String[] args) {
        // Target URL provided in Question 1(b)
        String urlString = "https://api.techstore.com:8443/products/category/electronics?sort=price&limit=20#reviews";

        try {
            // Using URI to safely construct and parse the URL
            URI uri = new URI(urlString);
            URL url = uri.toURL();

            // Extract individual URL components
            String protocol = url.getProtocol();
            String host = url.getHost();
            int port = url.getPort();
            String path = url.getPath();
            String query = url.getQuery();
            String fragment = url.getRef(); // Fragment / Anchor (#)

            // Display components
            System.out.println("==================================================");
            System.out.println("                 URL PARSER RESULT                ");
            System.out.println("==================================================");
            System.out.println("Protocol : " + protocol);
            System.out.println("Host     : " + host);
            System.out.println("Port     : " + port);
            System.out.println("Path     : " + path);
            System.out.println("Fragment : " + fragment);
            System.out.println("--------------------------------------------------");
            System.out.println("Query Parameters (Key-Value Pairs):");

            // Extract and parse query parameters into key-value pairs
            if (query != null && !query.isEmpty()) {
                String[] pairs = query.split("&");
                for (String pair : pairs) {
                    String[] keyValue = pair.split("=");
                    if (keyValue.length == 2) {
                        System.out.println("  • " + keyValue[0] + " = " + keyValue[1]);
                    } else if (keyValue.length == 1) {
                        System.out.println("  • " + keyValue[0] + " = (no value)");
                    }
                }
            } else {
                System.out.println("  (No query parameters found)");
            }
            System.out.println("==================================================");

        } catch (URISyntaxException | MalformedURLException e) {
            System.err.println("Error parsing URL: " + e.getMessage());
        }
    }
}

