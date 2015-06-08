package pt.isel.mpd.monitrgw.service1;

import pt.isel.mpd.monitrgw.model.IMonitrMarketData;
import pt.isel.mpd.monitrgw.service1.model.MonitrMarketData;
import pt.isel.mpd.monitrgw.webapi.MonitrApi;
import pt.isel.mpd.monitrgw.webapi.dto.MonitrMarketDto;

import java.util.stream.Stream;

/**
 * Created by mcarvalho on 28-05-2015.
 */
public class MonitrServiceEager {

    public static Stream<IMonitrMarketData> GetLastNews(){
        MonitrMarketDto dto = MonitrApi.GetLastNews(); // 1 http get request
        return dto.data
                .stream()
                .map(MonitrMarketData::valueOf); // 1 lazy Http request per item + 1 http request

    }
}
