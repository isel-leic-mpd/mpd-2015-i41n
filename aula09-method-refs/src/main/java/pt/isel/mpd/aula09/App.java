/*
 * Copyright (C) 2015 Miguel Gamboa at CCISEL
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package pt.isel.mpd.aula09;

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 *
 * @author Miguel Gamboa at CCISEL
 */
public class App {
    
    public static <T> void foreach(Iterable<T> src, Consumer<T> c){
        for (T item : src) {
            c.accept(item);
        }
    }
    
    
    /**
     * Um projeccção. Em .Net a função equivalente é um Select.
     */
    public static <T, R> List<R> map(Iterable<T> src, Function<T, R> c){
        List<R> res = new ArrayList<R>();
        for (T item : src) {
            res.add(c.apply(item)); // no exemplo do main ocorre um boxing
        }
        return res;
    }
    
    public static void main(String [] args){
        List<String> s= Arrays.asList("ISEL", "Ola", "super");
        foreach(s, out::println); // 2ª forma Method reference => target reference = instancia
        
        // foreach(map(s, (item) -> item.length()), out::println);
        
        foreach(
                map(s, String::length), //3ª forma Method Reference => 
                out::println);
        
        List<String> names = Arrays.asList("Porto", "Lisboa", "Faro");
        
        // City::new representa a chamada a: new City(....), que recebe
        // uma String e retorna um novo objecto City
        List<City> cities = map(names, City::new);
        
        // cities = map(names, (name) -> new City(name));
    }
}
