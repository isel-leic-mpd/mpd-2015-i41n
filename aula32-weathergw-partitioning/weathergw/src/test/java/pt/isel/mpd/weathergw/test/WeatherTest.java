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

import static java.util.stream.StreamSupport.stream;
import static pt.isel.mpd.util.Queries.filter;

import pt.isel.mpd.util.Queries;
import pt.isel.mpd.weathergw.WeatherInfo;
import pt.isel.mpd.weathergw.WeatherParserFromFile;

/**
 *
 * @author Miguel Gamboa at CCISEL
 */
public class WeatherTest extends TestCase{
    
    public void test_parse_weather_info() throws ParseException {
        Iterable<WeatherInfo> l = new WeatherParserFromFile().apply("Lisbon");
        Assert.assertEquals(92, stream(l.spliterator(), false).count());
    }

    /**
     * 5th attempt
     */
    public void test_search_by_predicate_with_lambda() throws ParseException {
      Iterable<WeatherInfo> src = new WeatherParserFromFile().apply("Lisbon");
        Assert.assertEquals(8,
                Queries.filter(src, w -> w.tempC == 18).size());
        
        Assert.assertEquals(42,
                Queries.filter(src, w -> w.weatherDesc.equals("Sunny")).size());
    }
    
    /**
     * 6th attempt
     */ 
    public void test_search_by_generic_predicate() throws ParseException{
        Iterable<WeatherInfo> src = new WeatherParserFromFile().apply("Lisbon");
        Assert.assertEquals(8,
                filter(src, w -> w.tempC == 18).size());
        Assert.assertEquals(42,
                filter(src, w -> w.weatherDesc.equals("Sunny")).size());
    }
}
