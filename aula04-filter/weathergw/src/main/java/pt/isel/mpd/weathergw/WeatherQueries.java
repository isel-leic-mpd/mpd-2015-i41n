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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Miguel Gamboa at CCISEL
 */
public class WeatherQueries {

    public static List<WeatherInfo> filterSunnyDays(List<WeatherInfo> src) {
        List<WeatherInfo> res = new ArrayList<>();
        for (WeatherInfo l : src) {
            if (l.weatherDesc.equals("Sunny")) {
                res.add(l);
            }
        }
        return res;
    }

    public static List<WeatherInfo> filterWeatherDesc(List<WeatherInfo> src, String desc) {
        List<WeatherInfo> res = new ArrayList<>();
        for (WeatherInfo l : src) {
            if (l.weatherDesc.equals(desc)) {
                res.add(l);
            }
        }
        return res;
    }

    public static List<WeatherInfo> filterWeather(
            List<WeatherInfo> src,
            Date date,
            Integer tempC,
            String desc,
            Double precipMM,
            Integer feelsLikeC) {
        List<WeatherInfo> res = new ArrayList<>();
        for (WeatherInfo l : src) {
            if (tempC != null && !tempC.equals(l.tempC)) {
                continue;
            }
            if (desc != null && !desc.equals(l.weatherDesc)) {
                continue;
            }
            if (precipMM != null && !precipMM.equals(l.precipMM)) {
                continue;
            }
            if (feelsLikeC != null && !feelsLikeC.equals(l.feelsLikeC)) {
                continue;
            }
            if (date != null && !l.date.equals(date)) {
                continue;
            }
            res.add(l);
        }
        return res;
    }

    
    public static List<WeatherInfo> filterWeather(
            List<WeatherInfo> src, WeatherInfo criteria)
    {
        List<WeatherInfo> res = new ArrayList<>();
        for (WeatherInfo l : src) {
            if (criteria.equals(l)) {
                res.add(l);
            }
        }
        return res;        
    }
    
    public static List<WeatherInfo> filterWeather(
        List<WeatherInfo> src, WeatherPredicate criteria)
    {
        List<WeatherInfo> res = new ArrayList<>();
        for (WeatherInfo l : src) {
            if (criteria.test(l)) {
                res.add(l);
            }
        }
        return res;        
    }
}
