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

import java.text.ParseException;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 *
 * @author Miguel Gamboa at CCISEL
 */
public class App {
    
    public static void print(List<WeatherInfo> src){
        for (WeatherInfo l : src) {
            System.out.println(l);
        }
    }

    static class ReversableCmp<T, R extends Comparable<R>> implements Comparator<T>{

        private final Function<T, R> sup;

        public ReversableCmp(Function<T, R> sup) {
            this.sup = sup;
        }

        @Override
        public int compare(T o1, T o2) {
            return sup.apply(o1).compareTo(sup.apply(o2));
        }

        @Override
        public Comparator<T> reversed() {
            return (o1, o2) -> compare(o2, o1);
        }

        public <R2 extends Comparable<R2>> ReversableCmp<T, R2> andThen(Function<T, R2> sup2) {
            ReversableCmp cmp2 = new ReversableCmp(sup2);
            return new ReversableCmp<T, R2>(sup2){
                @Override
                public int compare(T o1, T o2) {
                    int res = ReversableCmp.this.compare(o1, o2);
                    return res != 0? res : cmp2.compare(o1, o2);
                }
            };
        }
    }

    static <T, R extends Comparable<R>> ReversableCmp<T, R> comparing(Function<T, R> sup){
        return new ReversableCmp<>(sup);
    }

    public static void main(String [] args) throws ParseException{

        CityLazy lis = new CityLazy(
                "Lisbon", 
                WeatherParserFromHttp::parseWeather);

        List<WeatherInfo> h = lis.getWeatherHistory();
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

                print(h);
    }
}
