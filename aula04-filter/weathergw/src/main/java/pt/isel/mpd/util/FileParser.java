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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.ClassLoader.getSystemResourceAsStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Miguel Gamboa at CCISEL
 */
public class FileParser {

    /**
     * TPC: Implementar um Iterable lazy que retorna as linhas do resource na
     * forma de Strings. NÃO podem instanciar arrays nem colecções sauxiliares.
     */
    public static Iterable<String> parseResourceAsIterable(final String path){
 
        return new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                return new Iterator<String>() {
                    
                    final BufferedReader in = new BufferedReader(new InputStreamReader(
                        getSystemResourceAsStream(path)));
                    boolean streamClosed = false;
                    
                    @Override
                    public boolean hasNext() {
                        if(streamClosed)
                            return false;
                        try {
                            streamClosed = !in.ready();
                            if(streamClosed){
                                in.close();
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        return !streamClosed;
                    }

                    @Override
                    public String next() {
                        String line = null;
                        try {
                            line = in.readLine();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        return line;
                    }
                };
            }
        };
    }
}
