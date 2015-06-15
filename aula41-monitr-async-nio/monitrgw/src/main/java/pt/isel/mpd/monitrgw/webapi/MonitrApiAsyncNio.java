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
import com.ning.http.client.Response;
import org.apache.http.util.EntityUtils;
import pt.isel.mpd.monitrgw.webapi.dto.MonitrMarketDto;
import pt.isel.mpd.monitrgw.webapi.dto.MonitrStockAnalysisDto;
import pt.isel.mpd.monitrgw.webapi.dto.MonitrStockAnalysisDtoData;
import pt.isel.mpd.monitrgw.webapi.dto.MonitrStockDetailsDto;
import pt.isel.mpd.util.HttpGw;
import pt.isel.mpd.util.HttpGwAsyncNio;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class MonitrApiAsyncNio implements AutoCloseable{
    private static final String MONITR_API_KEY = "1e3f8640-f754-11e3-97e9-179fff8a3cc5";
    private static final String MONITR_URI = "http://api.monitr.com/api";

    private final Gson jsonReader;
    private final HttpGwAsyncNio asyncClient;

    public MonitrApiAsyncNio() {
        this(new Gson(), new HttpGwAsyncNio());
    }

    public MonitrApiAsyncNio(Gson jsonReader, HttpGwAsyncNio asyncClient) {
        this.jsonReader = jsonReader;
        this.asyncClient = asyncClient;
    }

    public CompletableFuture<MonitrMarketDto> GetLastNews()
    {
        return callMonitrAction(
                MonitrMarketDto.class,
                "/v1/market/news?apikey=%s",
                MONITR_API_KEY);
    }

    public CompletableFuture<MonitrStockDetailsDto> GetStockDetails(String stockSymbol){
        return callMonitrAction(
                MonitrStockDetailsDto.class,
                "/v2/symbol?apikey=%s&symbol=%s",
                MONITR_API_KEY,
                stockSymbol);
    }

    public CompletableFuture<MonitrStockAnalysisDtoData> GetStockAnalysis(String stockSymbol){
        CompletableFuture<MonitrStockAnalysisDto> stockAnalysis = callMonitrAction(
                MonitrStockAnalysisDto.class,
                "/v2/symbol/mentions?apikey=%s&symbol=%s&startDay=0&endDay=1",
                MONITR_API_KEY,
                stockSymbol);
        return stockAnalysis.thenApply(saDto -> saDto.analysis.get(0));
    }

    private <T> CompletableFuture<T> callMonitrAction(Class<T> destKlass, String action, Object...args) {
        final String uri = String.format(action, args);
        return asyncClient
                .getDataAsync(MONITR_URI + uri)
                .thenApply(resp -> fromJson(resp, destKlass));
    }

    private <T> T fromJson(Response resp, Class<T> destKlass) {
        try {
            return jsonReader.fromJson(resp.getResponseBody(), destKlass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        asyncClient.close();
    }
}
