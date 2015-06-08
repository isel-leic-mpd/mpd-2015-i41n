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
package pt.isel.mpd.monitrgw.webapi.dto;

public class MonitrMarketDtoData {
    public final String market;
    public final String title;
    public final String symbol;
    public final String link;
    public final long timeInMilis;
    public final String domain;

    public MonitrMarketDtoData(String market, String title, String symbol, String link, long timeInMilis, String domain) {
        this.market = market;
        this.title = title;
        this.symbol = symbol;
        this.link = link;
        this.timeInMilis = timeInMilis;
        this.domain = domain;
    }

    @Override
    public String toString() {
        return "MonitrMarketDtoData{" +
                "market='" + market + '\'' +
                ", symbol='" + symbol + '\'' +
                ", link='" + link + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}