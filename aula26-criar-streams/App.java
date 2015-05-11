
import java.nio.file.*;
import java.util.*;
import static java.util.Arrays.stream;
import java.util.stream.*;

public class App{

    public static void main(String [] args) throws Exception{
        Stream.of("Ola", "isel", "super")
            .forEach(System.out::println);
            
        int[] src = {1, 2, 3, 4, 5, 6, 7, 8, 9};
         
        stream(src)
            .forEach(n -> System.out.print(n + " "));
        
        System.out.println();        
        
        stream(src)
            .filter(n -> n % 2 == 0)
            .forEach(n -> System.out.print(n + " "));
            
        System.out.println();
        
        /*
        Files.lines(Paths.get("i41N-2015-names.txt"))
            .map(line -> stream(line.split(" ")))
            .forEach(
               line -> line.forEach(
                  word -> System.out.print(word + " ")
               )
            );
        */
        Files.lines(Paths.get("i41N-2015-names.txt"))
            .flatMap(line -> stream(line.split(" ")))
            .forEach(word -> System.out.print(word + " "));

        System.out.println();
        
    }
}