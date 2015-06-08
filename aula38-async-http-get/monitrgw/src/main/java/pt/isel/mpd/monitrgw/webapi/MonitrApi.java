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
package pt.isel.mpd.monitrgw.webapi;

import com.google.gson.Gson;
import org.apache.http.util.EntityUtils;
import pt.isel.mpd.monitrgw.webapi.dto.*;
import pt.isel.mpd.util.HttpGw;

public class MonitrApi {
    private static final String MONITR_API_KEY = "1e3f8640-f754-11e3-97e9-179fff8a3cc5";
    private static final String MONITR_URI = "http://api.monitr.com/api";
    private static final Gson jsonReader = new Gson();

    public static MonitrMarketDto GetLastNews()
    {
        return callMonitrAction(
                MonitrMarketDto.class,
                "/v1/market/news?apikey=%s",
                MONITR_API_KEY);
    }

    public static MonitrStockDetailsDto GetStockDetails(String stockSymbol){
        return callMonitrAction(
                MonitrStockDetailsDto.class,
                "/v2/symbol?apikey=%s&symbol=%s",
                MONITR_API_KEY,
                stockSymbol);
    }

    public static MonitrStockAnalysisDtoData GetStockAnalysis(String stockSymbol){
        MonitrStockAnalysisDto stockAnalysis = callMonitrAction(
                MonitrStockAnalysisDto.class,
                "/v2/symbol/mentions?apikey=%s&symbol=%s&startDay=0&endDay=1",
                MONITR_API_KEY,
                stockSymbol);
        return stockAnalysis.analysis.get(0);
    }

    private static <T> T callMonitrAction(Class<T> destKlass, String action, Object...args) {
        final String uri = String.format(action, args);
        return HttpGw.getData(
                MONITR_URI + uri,
                ent -> jsonReader.fromJson(EntityUtils.toString(ent), destKlass));
    }
}
