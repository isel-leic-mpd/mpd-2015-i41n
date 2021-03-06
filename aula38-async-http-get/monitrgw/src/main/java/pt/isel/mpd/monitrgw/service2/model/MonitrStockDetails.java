package pt.isel.mpd.monitrgw.service2.model;

import pt.isel.mpd.monitrgw.model.IMonitrStockAnalysisData;
import pt.isel.mpd.monitrgw.model.IMonitrStockDetails;
import pt.isel.mpd.monitrgw.webapi.MonitrApi;
import pt.isel.mpd.monitrgw.webapi.dto.MonitrStockAnalysisDtoData;
import pt.isel.mpd.monitrgw.webapi.dto.MonitrStockDetailsDto;
import pt.isel.mpd.util.LazyInit;

import java.util.List;
import java.util.function.Supplier;

/**
 * Created by mcarvalho on 28-05-2015.
 */
public class MonitrStockDetails implements IMonitrStockDetails{
    private final String industry;
    private final String name;
    private final String sector;
    private final String symbol;
    private final int status;
    private final String description;
    private final List<String> alias;
    private final List<String> competitors;
    private final Supplier<IMonitrStockAnalysisData> analysis;

    public MonitrStockDetails(String industry, String name, String sector, String symbol, int status, String description, List<String> alias, List<String> competitors) {
        this.industry = industry;
        this.name = name;
        this.sector = sector;
        this.symbol = symbol;
        this.status = status;
        this.description = description;
        this.alias = alias;
        this.competitors = competitors;
        this.analysis = LazyInit.lazily(
                () -> MonitrStockAnalysisData.valueOf(symbol, name, MonitrApi.GetStockAnalysis(symbol)));
    }

    @Override
    public String getIndustry() {
        return industry;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSector() {
        return sector;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public List<String> getAlias() {
        return alias;
    }

    public List<String> getCompetitors() {
        return competitors;
    }

    @Override
    public IMonitrStockAnalysisData getAnalysis() {
        return analysis.get();
    }


    @Override
    public String toString() {
        return "MonitrStockDetails{" +
                "industry='" + industry + '\'' +
                ", name='" + name + '\'' +
                ", sector='" + sector + '\'' +
                ", symbol='" + symbol + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", alias=" + alias +
                ", competitors=" + competitors +
                ", analysis=" + analysis.get() +
                '}';
    }

    public static IMonitrStockDetails valueOf(MonitrStockDetailsDto dto) {
        return new MonitrStockDetails(
                dto.industry,
                dto.name,
                dto.sector,
                dto.symbol,
                dto.status,
                dto.description,
                dto.alias,
                dto.competitors);
    }


}
