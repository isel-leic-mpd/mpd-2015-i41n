package pt.isel.mpd.monitrgw.service2.model;

import pt.isel.mpd.monitrgw.model.IMonitrMarketData;
import pt.isel.mpd.monitrgw.model.IMonitrStockDetails;
import pt.isel.mpd.monitrgw.webapi.MonitrApi;
import pt.isel.mpd.monitrgw.webapi.dto.MonitrMarketDtoData;
import pt.isel.mpd.monitrgw.webapi.dto.MonitrStockDetailsDto;
import pt.isel.mpd.util.LazyInit;

import java.util.function.Supplier;

import static pt.isel.mpd.util.LazyInit.lazily;

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
    private final Supplier<IMonitrStockDetails> stockDetails;

    public MonitrMarketData(String market, String title, String stockSymbol, String link, long timeInMilis, String domain) {
        this.market = market;
        this.title = title;
        this.stockSymbol = stockSymbol;
        this.link = link;
        this.timeInMilis = timeInMilis;
        this.domain = domain;

        // MonitrStockDetailsDto  ---> MonitrStockDetails
        //
        stockDetails = lazily(() -> MonitrStockDetails.valueOf(MonitrApi.GetStockDetails(stockSymbol)));
    }

    public static MonitrMarketData valueOf(MonitrMarketDtoData dto){
        return new MonitrMarketData(
                dto.market,
                dto.title,
                dto.symbol,
                dto.link,
                dto.timeInMilis,
                dto.domain);
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
        return stockDetails.get();
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
                ", stockDetails=" + stockDetails.get() +
                '}';
    }
}
