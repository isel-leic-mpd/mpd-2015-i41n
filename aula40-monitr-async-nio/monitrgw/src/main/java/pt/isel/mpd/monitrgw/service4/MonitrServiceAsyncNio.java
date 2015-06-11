package pt.isel.mpd.monitrgw.service4;

import pt.isel.mpd.monitrgw.model.IMonitrMarketData;
import pt.isel.mpd.monitrgw.model.IMonitrStockDetails;
import pt.isel.mpd.monitrgw.service4.model.MonitrMarketData;
import pt.isel.mpd.monitrgw.service4.model.MonitrStockDetails;
import pt.isel.mpd.monitrgw.webapi.MonitrApi;
import pt.isel.mpd.monitrgw.webapi.MonitrApiAsyncNio;
import pt.isel.mpd.monitrgw.webapi.dto.MonitrMarketDto;
import pt.isel.mpd.monitrgw.webapi.dto.MonitrMarketDtoData;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/**
 * Created by mcarvalho on 28-05-2015.
 */
public class MonitrServiceAsyncNio implements AutoCloseable{
    final MonitrApiAsyncNio monitrApi;

    public MonitrServiceAsyncNio(MonitrApiAsyncNio monitrApi) {
        this.monitrApi = monitrApi;
    }

    public MonitrServiceAsyncNio() {
        this(new MonitrApiAsyncNio());
    }

    public CompletableFuture<Stream<IMonitrMarketData>> GetLastNews(){
        return monitrApi.GetLastNews() // --> CompletableFuture<MonitrMarketDto>
                .thenApply(dto -> dto
                        .data
                        .stream()
                        .map(dtoData -> MonitrMarketData.valueOf(dtoData, GetStockDetails(dtoData)))); // 1 Http request to MonitrStockDetails --> 1 Http request to StockAnalysis
    }

    private CompletableFuture<IMonitrStockDetails> GetStockDetails(MonitrMarketDtoData dtoData){
        return monitrApi
                .GetStockDetails(dtoData.symbol)
                .thenApply(dtoStock -> MonitrStockDetails.valueOf(dtoStock));
    }

    @Override
    public void close() throws Exception {
        monitrApi.close();
    }
}
