
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class App{

    public static Stream<Integer> evenNumbersBoxed(){
        int [] nr = {0}; // estado partilhado mutável.
        return Stream.generate(() -> {int tmp = nr[0]; nr[0]+=2; return tmp;});
    }
    
    public static IntStream evenNumbers(){
        int [] nr = {0};
        return IntStream.generate(() -> {int tmp = nr[0]; nr[0]+=2; return tmp;});
    }
    
    
    
    public static void main(String [] args) throws Exception{
        evenNumbersBoxed().limit(20).forEach(n -> System.out.print(n + " "));
        System.out.println();
        evenNumbers().limit(20).forEach(n -> System.out.print(n + " "));
        System.out.println();
        Stream.generate(Math::random).limit(20).forEach(n -> System.out.print(n + " "));
    }
}