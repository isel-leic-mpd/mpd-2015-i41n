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
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Miguel Gamboa at CCISEL
 */
public class WeatherInfo {
    
    private static final SimpleDateFormat dateParser = 
            new SimpleDateFormat("yyyy-MM-dd");
    
    public final Date date; // index 0
    public final int tempC; // index 2
    public final String weatherDesc; // index 10
    public final double  precipMM; // index 11;
    public final int feelsLikeC; // index 24;

    public WeatherInfo(Date date, int tempC, String weatherDesc, double precipMM, int feelsLikeC) {
        this.date = date;
        this.tempC = tempC;
        this.weatherDesc = weatherDesc;
        this.precipMM = precipMM;
        this.feelsLikeC = feelsLikeC;
    }

    @Override
    public String toString() {
        return "LocalInfo{" + "date=" + date + ", tempC=" + tempC + ", weatherDesc=" + weatherDesc + ", precipMM=" + precipMM + ", feelsLikeC=" + feelsLikeC + '}';
    }

    
    /**
     * Hourly information follows below the day according to the format of 
     * /past-weather resource of the World Weather Online API
     */
    public static WeatherInfo valueOf(String input) throws ParseException{
        String[] data = input.split(",");
        return new WeatherInfo(
                dateParser.parse(data[0]), 
                Integer.parseInt(data[2]),
                data[10],
                Double.parseDouble(data[11]),
                Integer.parseInt(data[24]));
    }
}
