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

import pt.isel.mpd.util.Averager;
import pt.isel.mpd.util.Queries;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.lang.System.out;
import static pt.isel.mpd.util.Queries.*;

/**
 *
 * @author Miguel Gamboa at CCISEL
 */
public class App {

    public static void testComparators(List<WeatherInfo> h){
        // h.sort((w1, w2) -> ((Double)w1.precipMM).compareTo(w2.precipMM));
        // h.sort((w1, w2) -> w1.weatherDesc.compareTo(w2.weatherDesc));
        // h.sort(comparing(WeatherInfo::getTempC).reversed());
        /*
        h.sort(
                comparing(WeatherInfo::getWeatherDesc).andThen(WeatherInfo::getTempC)
                        .andThen(WeatherInfo::getPrecipMM));
        */

        h.sort(Comparator
                .comparing(WeatherInfo::getWeatherDesc)
                .thenComparing(WeatherInfo::getTempC)
                .thenComparing(WeatherInfo::getPrecipMM));

        //foreach(h, System.out::println);
    }

    public static void testEageQueries(List<WeatherInfo> h){
        Consumer<String> counter = new Consumer<String>() {
            int val = 0;
            public void accept(String label) { out.println(label + val++); }
        };


        List<String> descs = limit(
                    map(
                        filter(
                            h,
                            w -> {counter.accept("Filtering... "); return w.getTempC() > 20;}),
                        w -> {counter.accept("Mapping ... "); return w.getWeatherDesc();}),
                    5);
    }



    public static void main(String [] args) throws ParseException{

        CityLazy lis = new CityLazy(
                "Lisbon", 
                new WeatherParserFromFile());

        List<WeatherInfo> h = lis.getWeatherHistory();

        h.stream()
                .filter(w -> w.getTempC() > 15)
                .findFirst()
                .ifPresent(w -> out.println(w));


        repeat("Average foreach: ", () -> averageForeach(h.stream(), WeatherInfo::getTempC));
        repeat("Average: ", () -> averageForeach(h.parallelStream(), WeatherInfo::getTempC));

        repeat("Average reduce: ", () -> h.stream()
                .map(w -> new Averager(1, w.getTempC()))
                .reduce((prev, next) -> prev.add(next))
                .get()
                .average());

        repeat("Average parallel: ", () -> h.parallelStream()
                .map(w -> new Averager(1, w.getTempC()))
                .reduce(new Averager(0, 0), (prev, next) -> prev.add(next))
                .average());

        /*
        List<String> data = Arrays.asList(null, "Ola", null, "isel", null, "super", null, "Ola", "super");
        String res = data.stream()
                .findAny()
                .get();

        out.println(res);
        */
    }
}
