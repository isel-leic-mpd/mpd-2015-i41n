/*
 * Copyright (C) Error: on line 4, column 33 in Templates/Licenses/license-default.txt
 The string doesn't match the expected date/time format. The string to parse was: "16/mar/2015". The expected format was: "MMM d, yyyy". Miguel Gamboa at CCISEL
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
package pt.isel.mpd.aula06;

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

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
    public static void foreach(int[] src, IntConsumer c){
        for (int item : src) {
            c.accept(item);
        }
    }
    
    /**
     * Um projeccção. Em .Net a função equivalente é um Select.
     */
    public static <T, R> List<R> map(Iterable<T> src, Function<T, R> c){
        List<R> res = new ArrayList<>();
        for (T item : src) {
            res.add(c.apply(item)); // no exemplo do main ocorre um boxing
        }
        return res;
    }
    
    public static <T> int[] map(List<T> src, ToIntFunction<T> c){
        int[] res = new int[src.size()];
        int idx = 0;
        for (T item : src) {
            res[idx++] = c.applyAsInt(item);
        }
        return res;
    }
    
    public static void main(String [] args){
        List<String> a = Arrays.asList("Ola", "ISEL", "Mundo");
        foreach(a, s -> out.println(s));
        
       
        ToIntFunction<String> toInt = s -> s.length();
        int[] wordsLength = map(a, toInt);
        foreach(wordsLength, n -> out.println(n));
    }  
}
