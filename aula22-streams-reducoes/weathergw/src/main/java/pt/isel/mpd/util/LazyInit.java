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

import java.util.function.Supplier;

/**
 *
 * @author Miguel Gamboa at CCISEL
 */
public class LazyInit {
    
    static class Initializer<T> implements Supplier<T>{

        private Supplier<T> src;

        public Initializer(Supplier<T> s) {
            this.src = () ->{
                T val = s.get();
                this.src = () -> val;
                return val;
            };
        }
        
        @Override
        public T get() {
            return src.get();
        }
    }
    
    public static <T> Supplier<T> lazily(Supplier<T> initialization){
        return new Initializer<>(initialization);
    }
    
    
    /* Flag Version
    public static <T> Supplier<T> lazily(Supplier<T> initialization){
        return new Supplier<T>(){
            T cache;
            @Override   
            public T get() {
                if(cache == null)
                    cache = initialization.get();
                return cache;
            }
        };
    }
    */
}
