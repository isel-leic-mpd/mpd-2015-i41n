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
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.Optional.empty;
import static java.util.Optional.of;

/**
 *
 * @author Miguel Gamboa at CCISEL
 */
public class Queries {

    static <T, R extends Comparable<R>> ReversableCmp<T, R> comparing(Function<T, R> sup){
        return new ReversableCmp<>(sup);
    }

    public static <T> double averageForeach(Stream<T> data, Function<T, Integer> prop){
        MutableAverager ave = new MutableAverager();
        data
                .map(temp -> prop.apply(temp))
                .forEach(temp -> ave.add(temp));
        return ave.average();
    }

    public static <T> void repeat(String label, Supplier<T> sup){
        System.out.print(label + ": ");
        for (int i = 0; i < 10; i++) {
            T res = sup.get();
            System.out.print(res + " ");
        }
        System.out.println();
    }

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

    public static <T> Iterable<T> distinct(Iterable<T> src) {

        return () -> new Iterator<T>() {
            Iterator<T> iter = src.iterator();
            Set<T>  returned = new HashSet<>();
            Optional<T> nextItem = step();

            private Optional<T> step(){
                while(iter.hasNext()){
                    T tmp = iter.next();
                    if(!returned.contains(tmp))
                        return of(tmp);
                }
                return empty();
            }

            @Override
            public boolean hasNext() {
                return nextItem.isPresent();
            }

            @Override
            public T next() {
                if(hasNext()) {
                    T aux = nextItem.get();
                    returned.add(aux);
                    nextItem = step();
                    return aux;
                }
                else throw new NoSuchElementException();
            }
        };
    }
}
