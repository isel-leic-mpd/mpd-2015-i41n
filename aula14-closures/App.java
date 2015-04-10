import java.util.function.*;

class App{
    // double bar = 9;

    void foo(int v){
        final int n = v + 1;
        // n = 2 * n; => erro de compilação
        Function<Integer, String> f = r -> "res = " + (n + r);
    }


    public static void main(String [] args){
        new App().foo(7);
    
    }
}