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
import java.util.List;
import java.util.function.Consumer;

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
    
    public static void main(String [] args) throws ParseException{
        // List<WeatherInfo> l = WeatherParser.parseWeather();
        
        Consumer<String> c;
        
        
        IWeatherParser p = WeatherParserFromFile::parseWeather;
        
        City lis = new City(
                "Lisbon", 
                WeatherParserFromHttp::parseWeather);
        
        System.out.println(lis.getWeatherHistory());
    }
}
