import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class FirstPrimes{

    public static void main(String [] args){
        partitionPrimes(100)
            .entrySet()
            .stream()
            .forEach(System.out::println);

        partitionPrimesOpt(100)
            .entrySet()
            .stream()
            .forEach(System.out::println);

        System.out.println("Fastest primes: " + 
            measurePerformance(() -> partitionPrimes(1_000_000))+  " ms");
            
        System.out.println("Fastest primes opt: " + 
            measurePerformance(() -> partitionPrimesOpt(1_000_000)) +  " ms");
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    /****************************************************************/
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    private static Map<Boolean, List<Integer>> partitionPrimes(int max){
        return IntStream
            .rangeClosed(1, max)
            .boxed()
            .collect(
                Collectors.partitioningBy(FirstPrimes::isPrime)); // Predicate<Integer>
    }
    
    private static boolean isPrime(int candidate) {
        return candidate > 1 && 
            IntStream
                .rangeClosed(2, (int) Math.sqrt(candidate))
                .noneMatch(divisor -> candidate % divisor == 0);
    }
        
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    /****************************************************************/
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
  
    private static <T> List<T> takeWhile(List<T> src, Predicate<T> p) {
        int i = 0;
        for(T item : src){
            if(!p.test(item))
                return src.subList(0, i);
            i++;
        }
        return src;
    }
  
    private static boolean isPrimeOpt(List<Integer> primes, int candidate) {
        return candidate > 1 && 
            takeWhile(primes, divisor -> divisor <= (int) Math.sqrt(candidate))
                .stream()
                .noneMatch(divisor -> candidate % divisor == 0);
    }
    
    private static Map<Boolean, List<Integer>> partitionPrimesOpt(int max){
        return IntStream
            .rangeClosed(1, max)
            .boxed()
            .collect(
                () -> {
                    Map<Boolean, List<Integer>> res = new HashMap<>();
                    res.put(true, new ArrayList<>());
                    res.put(false, new ArrayList<>());
                    return res;
                },
                (map, elem) -> map.get(isPrimeOpt(map.get(true), elem) ).add(elem),
                (m1, m2) -> {
                    m1.get(true).addAll(m2.get(true));
                    m1.get(false).addAll(m2.get(false));
                  }
                );
    }
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