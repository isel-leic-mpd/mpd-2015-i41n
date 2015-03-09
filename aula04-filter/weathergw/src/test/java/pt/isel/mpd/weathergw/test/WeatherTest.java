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
package pt.isel.mpd.weathergw.test;

import java.text.ParseException;
import java.util.List;
import junit.framework.Assert;
import junit.framework.TestCase;
import pt.isel.mpd.weathergw.WeatherInfo;
import pt.isel.mpd.weathergw.WeatherParser;
import pt.isel.mpd.weathergw.WeatherPredicate;
import pt.isel.mpd.weathergw.WeatherQueries;

/**
 *
 * @author Miguel Gamboa at CCISEL
 */
public class WeatherTest extends TestCase{
    
    public void test_parse_weather_info() throws ParseException {
        List<WeatherInfo> l = WeatherParser.parseWeather();
        Assert.assertEquals(32, l.size());
    }
    
    /**
     * 1st attempt
     */
    public void test_sunny_days() throws ParseException {
        List<WeatherInfo> src = WeatherParser.parseWeather();
        Assert.assertEquals(13, WeatherQueries.filterSunnyDays(src).size());
    }
    
    /**
     * 2dn attempt
     */
    public void test_rain_days() throws ParseException {
        List<WeatherInfo> src = WeatherParser.parseWeather();
        Assert.assertEquals(
                5, 
                WeatherQueries.filterWeatherDesc(src, "Light rain shower").size());
    }
    
    /**
     * 3rd attempt
     */
    public void test_sunny_days_and_temp() throws ParseException {
        List<WeatherInfo> src = WeatherParser.parseWeather();
        Assert.assertEquals(2, 
                WeatherQueries.filterWeather(
                        src, null, 18, "Sunny", null, null).size());
    }
    
    public void test_search_criteria() throws ParseException {
        List<WeatherInfo> src = WeatherParser.parseWeather();
        Assert.assertEquals(2, 
                WeatherQueries.filterWeather(src, new WeatherInfo(null, 18, "Sunny", 0, 0){
                            @Override
                            public boolean equals(Object obj) {
                                WeatherInfo other = (WeatherInfo) obj;
                                return this.tempC == other.tempC 
                                        && this.weatherDesc.equals(other.weatherDesc);
                            }
                        }).size());
    }
    
    /**
     * 4th attempt
     */
    public void test_search_by_predicate() throws ParseException {
        List<WeatherInfo> src = WeatherParser.parseWeather();
        Assert.assertEquals(2, 
                WeatherQueries.filterWeather(
                        src, new WeatherPredicate() {
                            @Override
                            public boolean test(WeatherInfo w) {
                                return 18 == w.tempC 
                                        && "Sunny".equals(w.weatherDesc); 
                            }
                        }).size());
    }

    /**
     * 5th attempt
     */
    public void test_search_by_predicate_with_lambda() throws ParseException {
        List<WeatherInfo> src = WeatherParser.parseWeather();
        /*
        Assert.assertEquals(2, 
                WeatherQueries.filterWeather(
                        src, w -> 18 == w.tempC && "Sunny".equals(w.weatherDesc) 
                ).size());
        */
        
        Assert.assertEquals(2, 
                WeatherQueries.filterWeather(
                        src, (WeatherInfo w) -> {
                            return 18 == w.tempC && "Sunny".equals(w.weatherDesc);
                        }
                ).size());
    }
}
