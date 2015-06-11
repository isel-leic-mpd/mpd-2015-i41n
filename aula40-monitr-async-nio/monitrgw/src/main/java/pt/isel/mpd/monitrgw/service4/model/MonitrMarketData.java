package pt.isel.mpd.monitrgw.service4.model;

import pt.isel.mpd.monitrgw.model.IMonitrMarketData;
import pt.isel.mpd.monitrgw.model.IMonitrStockDetails;
import pt.isel.mpd.monitrgw.webapi.MonitrApi;
import pt.isel.mpd.monitrgw.webapi.dto.MonitrMarketDtoData;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by mcarvalho on 28-05-2015.
 */
public class MonitrMarketData implements IMonitrMarketData{
    private final String market;
    private final String title;
    private final String stockSymbol;
    private final String link;
    private final long timeInMilis;
    private final String domain;
    private final Future<IMonitrStockDetails> stockDetails;

    public MonitrMarketData(String market, String title, String stockSymbol, String link, long timeInMilis, String domain, Future<IMonitrStockDetails> stockDetails) {
        this.market = market;
        this.title = title;
        this.stockSymbol = stockSymbol;
        this.link = link;
        this.timeInMilis = timeInMilis;
        this.domain = domain;
        this.stockDetails = stockDetails;
    }

    public static MonitrMarketData valueOf(MonitrMarketDtoData dto, Future<IMonitrStockDetails> stockDetails){
        return new MonitrMarketData(
                dto.market,
                dto.title,
                dto.symbol,
                dto.link,
                dto.timeInMilis,
                dto.domain,
                stockDetails);
    }

    public String getMarket() {
        return market;
    }

    public String getTitle() {
        return title;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public String getLink() {
        return link;
    }

    public long getTimeInMilis() {
        return timeInMilis;
    }

    public String getDomain() {
        return domain;
    }

    public IMonitrStockDetails getStockDetails() {
        try {
            return stockDetails.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "MonitrMarketData{" +
                "market='" + market + '\'' +
                ", title='" + title + '\'' +
                ", stockSymbol='" + stockSymbol + '\'' +
                ", link='" + link + '\'' +
                ", timeInMilis=" + timeInMilis +
                ", domain='" + domain + '\'' +
                ", stockDetails=" + getStockDetails() +
                '}';
    }
}
