import java.util.function.Function;
import java.util.Map;
import java.util.HashMap;

class MapFunction<T, R> implements java.util.function.Function<T, R> {

    private Map<T, R> functionMap;
    private R value;

    public MapFunction(Map<T, R> map) { this.functionMap = map; }

    public Function<T, R> defaultVal(R value) { this.value = value; return this; }
    
    public R apply(T t) {
        R value = functionMap.get(t);
        if (value == null && this.value == null)
            throw new IllegalArgumentException("The key " + t.toString() + " doesn't exists");
        if (value == null)
            return this.value;
        return value;
    }
}
public class TestMapFuncWithThis{
    
    public static <K,V> MapFunction<K,V> forMap(Map<K,V> map){
        return new MapFunction<>(map);
    }
    
    public static void main(String [] args){
        Map<String, Double> rates = new HashMap<>();
        rates.put("USD", 1.0772);
        rates.put("SEK", 9.379);
        rates.put("BRL", 3.2328);
        rates.put("AUD", 1.3937);

        MapFunction<String, Double> ratesFn = forMap(rates);
        Function<String, Double> f2 = ratesFn.defaultVal(999.0);
        
        System.out.println(f2.apply("XPTO")); // => retorna o valor default
        
        double australianRate = ratesFn.apply("AUD"); // => 1.3937
        System.out.println(australianRate);
        double canadianRate = ratesFn.apply("CAD"); // => Não lança IllegalArgumentException!!!
        System.out.println(canadianRate);
        
        
        
    }
}