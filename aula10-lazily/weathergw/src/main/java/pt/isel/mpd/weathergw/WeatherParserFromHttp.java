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

import java.io.IOException;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author Miguel Gamboa at CCISEL
 */
public class WeatherParserFromHttp {
 
    private static final String LISBON_HISTORY = "data/weather-lisbon-history.csv";
    
    public static List<WeatherInfo> parseWeather(){
         try(CloseableHttpClient httpclient = HttpClients.createDefault()){
            /*
            * Method: HttpGet
            */
            HttpGet httpget = new HttpGet("http://api.worldweatheronline.com/free/v2/past-weather.ashx?q=Lisbon&format=csv&date=2015-2-15&enddate=2015-3-15&tp=24&key=25781444d49842dc5be040ff259c5");
            /*
            * HttpResponse
            */
            try(CloseableHttpResponse response = httpclient.execute(httpget)){
                
                HttpEntity ent = response.getEntity();
                
                return WeatherParserFromStream.parseWeather(ent.getContent());
            } 
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
