import java.util.function.*;
import java.util.stream.*;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // fib2
        fib3()
            .parallel()
            .limit(50) //LongStream
            .mapToObj(n -> (Long) n)          // Stream<Long>
            // .collect(Collectors.toList()) // List<Long>
            .forEach(n -> System.out.println(n + " "));
                
        System.out.println();
        
        
    }

    static class Node{
        final long prev, curr;
        public Node(long prev, long curr){ this.prev = prev; this.curr = curr; }
    }
    static LongStream fib3() {
        return Stream
            .iterate( new Node(0,1), a -> new Node(a.curr, a.prev + a.curr))
            .mapToLong(a -> a.prev);
            
    }
    
    static LongStream fib2() {
        long [] nrs = {0, 1};
        return LongStream.generate( () -> {
            long res = nrs[0]; 
            long next = nrs[0] + nrs[1];
            nrs[0] = nrs[1];
            nrs[1] = next;
            return res;
        });
    }
    
    static LongStream fib1() {
       
        return LongStream.generate(new LongSupplier() {
            private  long curr = 1;
            private long prev = 0; 
            @Override
            public long getAsLong() {
                long toReturn = prev;
                long next = prev + curr;
                prev = curr;
                curr = next;
                return toReturn;            
            }
        });
    }
}
 