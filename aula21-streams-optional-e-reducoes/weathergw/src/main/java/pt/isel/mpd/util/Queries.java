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
package pt.isel.mpd.util;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 *
 * @author Miguel Gamboa at CCISEL
 */
public class Queries {

    public static <T> List<T> filter( List<T> src, Predicate<T> criteria){
        List<T> res = new ArrayList<>();
        for (T l : src) {
            if (criteria.test(l)) {
                res.add(l);
            }
        }
        return res;        
    }
    public static <T, R> List<R> map(List<T> src, Function<T, R> c){
        List<R> res = new ArrayList<>();
        for (T item : src) {
            res.add(c.apply(item)); // no exemplo do main ocorre um boxing
        }
        return res;
    }

    public static <T> void foreach(Iterable<T> src, Consumer<T> c){
        for (T l : src) {
            c.accept(l);
        }
    }

    public static <T> List<T> limit(Iterable<T> src, int top) {
        List<T> res = new ArrayList<>();
        int i = 0;
        for(T item : src){
            if(i++ < top) res.add(item);
            else break;
        }
        return res;
    }

    public static <T> Iterable<T> distinct(Stream<T> src) {

        return () -> new Iterator<T>() {
            Iterator<T> iter = src.iterator();
            Set<T>  returned = new HashSet<>();

            @Override
            public boolean hasNext() {
                return iter.hasNext();
            }

            @Override
            public T next() {
                T res = iter.next();
                while(returned.contains(res) && iter.hasNext()) res = iter.next();
                returned.add(res);
                return res;
            }
        };
    }
}
