package pt.isel.mpd.funcs;

import java.util.function.Function;

/**
 * Created by mcarvalho on 20-04-2015.
 */
public class App {

    static int f(int x){
        return x + 1;
    }

    static int g(int x){
        return 2 * x;
    }

    public static void main(String [] args){
        Function<Integer, Integer> inc = App::f;
        Function<Integer, Integer> dup = App::g;

        //Function<Integer, Integer> composeIncWithDup = inc.compose(dup);
        Function<Integer, Integer> composeIncWithDup = dup.andThen(inc);

        System.out.println("incWithDup(3) = " + composeIncWithDup.apply(3));
        System.out.println("incWithDup(5) = " + composeIncWithDup.apply(5));

        Function<Integer, Integer> dupWithInc = dup.compose(inc);

        System.out.println("dupWithInc (3) = " + dupWithInc .apply(3));
        System.out.println("dupWithInc (5) = " + dupWithInc .apply(5));
    }
}
