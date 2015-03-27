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
package pt.isel.mpd.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author Miguel Gamboa at CCISEL
 */
public class AutoMapper {
    /**
     * @param <T> T has getters and setters.
     * @param <R> R has final fields.
     */
    public static <T, R> R map(T src, Class<R> destKlass) throws IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException{
        R dest = destKlass.newInstance(); // Da excepção se nao existir ctor sem parametros
        Method[] getters = src.getClass().getDeclaredMethods();
        for (Method g : getters) {
            if(g.getName().startsWith("get")){
                Object val = g.invoke(src, null);
                Field f = destKlass.getDeclaredField(
                        g.getName().substring(3).toLowerCase());
                f.setAccessible(true);
                f.set(dest, val);
            }
        }
        return dest;
    }
}
