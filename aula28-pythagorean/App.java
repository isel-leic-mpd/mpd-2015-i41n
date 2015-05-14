import java.util.*;
import java.util.stream.*;

class Triple{
    final double a, b, c; 
    public Triple(double a, double b, double c){
        this.a = a; this.b = b; this.c = c;
    }
    public String toString(){
        return String.format("(%d, %d, %d)", (int) a, (int)b, (int)c);
    }
}

public class App{
    
    static Stream<Triple> bS(int a, int init, int end){
        return IntStream
                .rangeClosed(init, end)
                .mapToObj(b -> new Triple(a, b, Math.sqrt(a*a + b*b)))
                .filter(t -> t.c % 1 == 0);
    }
    static Stream<Triple> pythagoreanTriple(int init, int end){
        return IntStream
            .rangeClosed(init, end)
            .mapToObj(a -> a) // <=> boxed()
            .flatMap(a -> bS(a, init, end));
    }

    public static void main(String [] args){
        pythagoreanTriple(1, 100)
            .limit(5)
            .forEach(System.out::println);
            
    }
}