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
import static pt.isel.mpd.util.Queries.filter;
import pt.isel.mpd.weathergw.WeatherInfo;
import pt.isel.mpd.weathergw.WeatherParser;
import static pt.isel.mpd.weathergw.WeatherQueries.filterWeather;

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
     * 4th attempt
     */
    public void test_search_by_predicate() throws ParseException {
        List<WeatherInfo> src = WeatherParser.parseWeather();
        Assert.assertEquals(5, 
                filterWeather(src, new SelectWeatherByTempc((18))).size());
        
        Assert.assertEquals(13, 
                filterWeather(src, new SelectWeatherByDesc(("Sunny"))).size());
    }

    /**
     * 5th attempt
     */
    public void test_search_by_predicate_with_lambda() throws ParseException {
      List<WeatherInfo> src = WeatherParser.parseWeather();
        Assert.assertEquals(5, 
                filterWeather(src, w -> w.tempC == 18).size());
        
        Assert.assertEquals(13, 
                filterWeather(src, w -> w.weatherDesc.equals("Sunny")).size());
    }
    
    /**
     * 6th attempt
     */ 
    public void test_search_by_generic_predicate() throws ParseException{
        List<WeatherInfo> src = WeatherParser.parseWeather();
        Assert.assertEquals(5, 
                filter(src, w -> w.tempC == 18).size());
        Assert.assertEquals(13, 
                filter(src, w -> w.weatherDesc.equals("Sunny")).size());
    }
}
