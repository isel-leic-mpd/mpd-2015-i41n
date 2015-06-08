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
package pt.isel.mpd.monitrgw.model;

import pt.isel.mpd.monitrgw.webapi.MonitrApi;
import pt.isel.mpd.monitrgw.webapi.dto.MonitrMarketDtoData;

public interface IMonitrMarketData {

    public String getMarket();

    public String getTitle();

    public String getStockSymbol();

    public String getLink();

    public long getTimeInMilis();

    public String getDomain();

    public IMonitrStockDetails getStockDetails();

}
