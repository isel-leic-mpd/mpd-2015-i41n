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

import junit.framework.TestCase;
import pt.isel.mpd.weathergw.City;
import pt.isel.mpd.weathergw.CityLazy;

/**
 *
 * @author Miguel Gamboa at CCISEL
 */
public class TestCityLazyInit extends TestCase{

    public void test_city_eager_init(){
        City c = new City("Lisbon");
        assertEquals(32, c.getWeatherHistory().size());
        assertEquals(32, c.getWeatherHistory().size());
        assertEquals(2, "TPC");
    }
   
    /**
     * Controlar o numero de vezes que o parseWeather é chamado 
     * SEM adicionar campos ao WeatherParser.
     */
    public void test_city_lazy_init(){
        CityLazy c = new CityLazy("Lisbon");
        assertEquals(32, c.getWeatherHistory().size());
        assertEquals(32, c.getWeatherHistory().size());
        assertEquals(1, "TPC");
    }
}
