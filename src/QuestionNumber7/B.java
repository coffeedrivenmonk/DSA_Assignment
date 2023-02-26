package QuestionNumber7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class B {

    private static BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    static {
        queue.add("https://example.com");
    }


    private static final int MAX_THREADS = 10;


    private static HashSet<String> visited = new HashSet<>();

    public static void main(String[] args) throws InterruptedException {

        Thread[] threads = new Thread[MAX_THREADS];
        for (int i = 0; i < MAX_THREADS; i++) {
            threads[i] = new Thread(new Crawler());
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }

    private static class Crawler implements Runnable {
        public void run() {
            while (true) {
                try {

                    String url = queue.take();


                    if (visited.contains(url)) {
                        continue;
                    }


                    URL urlObj = new URL(url);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(urlObj.openStream()));


                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.contains("href=")) {
                            int startIndex = line.indexOf("href=") + 6;
                            int endIndex = line.indexOf("\"", startIndex);
                            String link = line.substring(startIndex, endIndex);


                            if (!link.startsWith("http")) {
                                link = urlObj.getProtocol() + "://" + urlObj.getHost() + link;
                            }


                            if (!visited.contains(link)) {
                                queue.put(link);
                            }
                        }
                    }


                    visited.add(url);
                } catch (Exception ignore) {}
            }
        }
    }
}
