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
                
        List<Integer> res2  = IntStream.rangeClosed(1, 10)
            // .parallel()
            .boxed() 
            .collect( ArrayList::new, List::add, List::addAll );
        System.out.println("collect() com method handles: " + res2);
        
        res2  = IntStream.rangeClosed(1, 10)
            // .parallel()
            .boxed() 
            .collect( new ToListCollector<>() );
            // .collect( myList() );
        System.out.println("collect() com Collector: " + res2);
    }
    
    private static <T> ToListCollector<T> myList(){
        return new ToListCollector<T>();
    }
}


class ToListCollector<T> implements Collector<T, List<T>, List<T>> {
    public Supplier<List<T>> supplier(){ 
        return ArrayList::new; 
    }
    public BiConsumer<List<T>, T> accumulator() { 
        return List::add; 
    }
    public BinaryOperator<List<T>> combiner() { 
        return (prev, curr) -> {prev.addAll(curr); return prev;}; 
    }
    public Function<List<T>, List<T>> finisher() { 
        return Function.identity(); 
    }
    public Set<Characteristics> characteristics(){
        HashSet<Characteristics> res = new HashSet<>();
        res.add(Characteristics.CONCURRENT);
        res.add(Characteristics.IDENTITY_FINISH);
        return res;
   }
}
