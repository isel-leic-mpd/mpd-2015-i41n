import java.util.function.*;
import java.util.stream.*;

public class Primes{

   public static <T> long measurePerformance(Supplier<T> action){
       long fastest = Long.MAX_VALUE;
       for (int i = 0; i < 5; i++) {
             long start = System.nanoTime();
             System.out.print(action.get() + " ");
             long duration = (System.nanoTime() - start) / 1_000_000; // mili seconds
             if (duration < fastest) fastest = duration;
       }
       System.out.println("\nMin Duration = " + fastest + " msecs");
       return fastest;
   }
   
    public static boolean isPrimeIterative(long nr){
        for(long i = 2; i < nr; i++)
            if(nr%i == 0)
                return false;
        return true;
    }
    
    public static boolean isPrimeStream(long nr){
        /*
        return IntStream.range(2, nr)
            .filter(i -> nr%i == 0)
            .findAny()
            .isPresent();
        */    
        return LongStream.range(2, nr)
            .parallel()
            .noneMatch(i -> nr%i == 0);
    }
   
   public static void main(String [] args){
        measurePerformance(() -> isPrimeStream(179_424_673));
        measurePerformance(() -> isPrimeIterative(179_424_673));
   }
}