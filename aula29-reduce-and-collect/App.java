import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class App{


    public static <T> List<T> mergeList(List<T> l1, List<T> l2){
        ArrayList<T> res = new ArrayList<>(); 
        res.addAll(l1);
        res.addAll(l2);
        return res;
    }

    public static void main(String [] args){
        
        
        // Não é Thread Safe
        ArrayList<Integer> res = new ArrayList<>(); // captura de contexto
        IntStream.rangeClosed(1, 10)
            // .parallel()
            .boxed() // .map(n -> new Integer(n))
            .reduce(0, (i, j) -> {res.add(j); return j; }); // O valor do i não é usado
        System.out.println("reduce() mutable: " + res);
        
        
        // THREAD SAFE
        List<Integer> res2  = IntStream.rangeClosed(1, 10)
            // .parallel()
            .boxed() 
            .map( Arrays::asList ) // Integer => List<Integer>
            .reduce( new ArrayList<Integer>(), App::mergeList);
        System.out.println("reduce() immutable: " + res2);
        
        // Não é THREAD SAFE
        IntStream.rangeClosed(1, 10)
            // .parallel()
            .boxed() 
            .map( nr -> new ArrayList<Integer>( Arrays.asList(nr) ) ) // Integer => List<Integer>
            .reduce((p, c) -> {p.addAll(c); return p; })
            .ifPresent(l -> System.out.println("reduce() mutable: " + l));
            
        res2  = IntStream.rangeClosed(1, 10)
            // .parallel()
            .boxed() 
            .collect(
                () -> new ArrayList<Integer>(),
                (l, temp) -> l.add(temp),
                (l1, l2) -> l1.addAll(l2)
            );
        System.out.println("collect() com Collector: " + res2);
        
        res2  = IntStream.rangeClosed(1, 10)
            // .parallel()
            .boxed() 
            .collect( ArrayList::new, List::add, List::addAll );
        System.out.println("collect() com method handles: " + res2);
    }

}
