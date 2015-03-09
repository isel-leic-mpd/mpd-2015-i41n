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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Miguel Gamboa at CCISEL
 */
public class WeatherParser {
 
    private static final String LISBON_HISTORY = "data/weather-lisbon-history.csv";
    
    public static List<LocalInfo> parseWeather() throws ParseException{
        List<LocalInfo> res = new ArrayList<>();
        
        Iterator<String> lines = 
                FileParser.parseResourceAsIterable(LISBON_HISTORY).iterator();
        
        while(lines.next().startsWith("#"));
        
        while(lines.hasNext()){
            lines.next(); // Skip Not Available or Daily Info
            String line = lines.next();     
            LocalInfo info = LocalInfo.valueOf(line);
            res.add(info);
        }
        return res;
    }
}
