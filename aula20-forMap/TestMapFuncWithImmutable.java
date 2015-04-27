import java.util.function.Function;
import java.util.Map;
import java.util.HashMap;

class MapFunction<K, V> implements Function<K, V>
{
	private final Map<K, V> map;
    
	public MapFunction(Map<K, V> map) {
		this.map = map==null?new HashMap<K,V>():map;
	}
	public static<K,V> MapFunction<K,V> forMap(Map<K,V> map){
		return new MapFunction<K,V>(map);
	}
	@Override
	public V apply(K key) {
		if(!map.containsKey(key))throw new IllegalArgumentException();
		return map.get(key);
	}
	public Function<K, V> defaultVal(V value){
		return (k)->map.getOrDefault(k, value);
	}
}

public class TestMapFuncWithImmutable{
    
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