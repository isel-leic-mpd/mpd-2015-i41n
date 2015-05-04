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
package pt.isel.mpd.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 *
 * @author Miguel Gamboa at CCISEL
 */
public class HttpGw {
    
    public static <T> T getData(String path, HttpRespFormatter<T> formatter){
        try(CloseableHttpClient httpclient = HttpClients.createDefault()){
            /*
            * Method: HttpGet
            */
            HttpGet httpget = new HttpGet(path);
            /*
            * HttpResponse
            */
            try(CloseableHttpResponse response = httpclient.execute(httpget)){
               
                return formatter.format(response.getEntity());
            } 
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
