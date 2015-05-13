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
package pt.isel.mpd.weathergw;

import static java.lang.ClassLoader.getSystemResource;
import static java.lang.ClassLoader.getSystemResourceAsStream;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Miguel Gamboa at CCISEL
 */
public class WeatherParserFromFile implements Function<String, Iterable<WeatherInfo>>{
 
    private static final String LISBON_HISTORY = "data/weather-lisbon-history-long.csv";

    static class OddLines<T> implements Predicate<T>{
        int count = 0;
        public boolean test(T t) {
            return count++ % 2 != 0;
        }
    }

    public Iterable<WeatherInfo> apply(String cityName){
            try(Stream<String> lines = Files.lines(Paths.get(getSystemResource(LISBON_HISTORY).toURI()))) {
                return lines.filter(l -> !l.startsWith("#"))
                        .skip(1)
                        .filter(new OddLines<String>())
                        .map(WeatherInfo::valueOf)
                        .collect(toList());
            } catch (Exception e) { throw new RuntimeException(e); }

    }
}
