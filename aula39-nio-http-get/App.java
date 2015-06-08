import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;
import java.util.*;

import com.ning.http.client.*;
import java.io.*;
import java.net.*;

public class App{

    static String getData(String uri){
        try( BufferedReader resp = 
            new BufferedReader(new InputStreamReader(new URL(uri).openStream()))) {
            String line, result = "";
            while ((line = resp.readLine()) != null) {
                result += line;
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    static Future<String> getDataAsync(String uri){
        return CompletableFuture.supplyAsync(() -> getData(uri));
    }
  
    static CompletableFuture<String> getDataAsyncNio(String uri){
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        CompletableFuture<String> promise = new CompletableFuture<>();
        asyncHttpClient
            .prepareGet(uri)
            .execute(new AsyncCompletionHandler<Response>(){
                @Override
                public Response onCompleted(Response response) throws Exception{
                    promise.complete(response.getResponseBody());
                    return response;
                }

            });
        return promise;
    }
  
    public static void main(String [] args) throws Exception{
        final String [] competitors = {"AAPL", "MSFT", "IBM","BBRY", "GOOG", "SNDK", "HPQ", "YNDX","BIDU","YHOO"};
        final String stockDetails = "http://api.monitr.com/api/v2/symbol?apikey=1e3f8640-f754-11e3-97e9-179fff8a3cc5&symbol=%s";
        long duration;
        
        System.out.println("Warm-up...");
        getData(String.format(stockDetails, "AAPL")).substring(0, 30);
        getDataAsyncNio(String.format(stockDetails, "AAPL")).get().substring(0, 30);
        System.out.println("READY");
        System.out.println();
       
        duration = measurePerformance( () -> {
            Arrays.stream(competitors)
                .map(stock -> String.format(stockDetails, stock))
                .map(uri -> getData(uri))
                .forEach(r -> System.out.println(r.substring(0, 30)));
        });
        System.out.println("Sequential: " + duration + " ms");
        System.out.println();

       
        duration = measurePerformance( () -> {
            List<Future<String>> res =  Arrays.stream(competitors)
                .map(stock -> String.format(stockDetails, stock))
                .map(uri -> getDataAsync(uri))
                .collect(Collectors.toList());
            for(Future<String> f : res)
                System.out.println(f.get().substring(0, 30));
        });
        System.out.println("ForkJoinPool: " + duration + " ms");
        System.out.println();

       duration = measurePerformance( () -> {
            Stream<CompletableFuture> res = Arrays.stream(competitors)
                .map(stock -> String.format(stockDetails, stock))
                .map(uri -> getDataAsyncNio(uri))
                .map(f -> f
                                .thenApply(s -> s.substring(0, 30))
                                .thenAccept(System.out::println));
        });
        System.out.println("Http Async Client NIO: " + duration + " ms");
        System.out.println();
        
        Scanner in = new Scanner(System.in);
        in.nextLine();
    }
    
    public static long measurePerformance(RunnableThrowable action) throws Exception {
     long fastest = Long.MAX_VALUE;
     for (int i = 0; i < 1; i++) {
      long start = System.nanoTime();
      action.run();
      long duration = (System.nanoTime() - start) / 1_000_000; // micro seconds
      if (duration < fastest) fastest = duration;
     }
     return fastest;
    }
    
    interface RunnableThrowable{
        public void run() throws Exception;
    }
}

