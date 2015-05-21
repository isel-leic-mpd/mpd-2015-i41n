import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class FirstPrimes{

    public static void main(String [] args){
        partitionPrimes(100)
            .entrySet()
            .stream()
            .forEach(System.out::println);
        
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    /****************************************************************/
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    private static Map<Boolean, List<Integer>> partitionPrimes(int n){
        return IntStream
            .rangeClosed(2, n)
            .boxed()
            .collect(
                Collectors.partitioningBy(candidate -> isPrime(candidate)));
    }
    
    private static boolean isPrime(long candidate) {
        return candidate > 1 && 
            IntStream
                .rangeClosed(2, (int) Math.sqrt(candidate))
                .noneMatch(divisor -> candidate % divisor == 0);
    }
        
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    /****************************************************************/
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
  
    
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    /****************************************************************/
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    public static long measurePerformance(Runnable action){
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 5; i++) {
            long start = System.nanoTime();
            action.run();
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println(duration + " ms");
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }
}