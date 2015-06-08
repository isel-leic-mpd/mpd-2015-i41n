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

package pt.isel.mpd.monitrgw.service1.model;

import pt.isel.mpd.monitrgw.model.IMonitrStockAnalysisData;
import pt.isel.mpd.monitrgw.webapi.dto.MonitrStockAnalysisDtoData;

public class MonitrStockAnalysisData implements IMonitrStockAnalysisData{
    private final String symbol;
    private final String name;
    private final int mentions;
    private final int totalSentiment;
    private final int averageSentiment;
    private final int positive;
    private final int negative;

    public MonitrStockAnalysisData(String symbol, String name, int mentions, int totalSentiment, int averageSentiment, int positive, int negative) {
        this.symbol = symbol;
        this.name = name;
        this.mentions = mentions;
        this.totalSentiment = totalSentiment;
        this.averageSentiment = averageSentiment;
        this.positive = positive;
        this.negative = negative;
    }

    public int getMentions() {
        return mentions;
    }

    public int getTotalSentiment() {
        return totalSentiment;
    }

    public int getAverageSentiment() {
        return averageSentiment;
    }

    public int getPositive() {
        return positive;
    }

    public int getNegative() {
        return negative;
    }

    @Override
    public String toString() {
        return "IMonitrStockAnalysisData{" +
                "symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", mentions=" + mentions +
                ", totalSentiment=" + totalSentiment +
                ", averageSentiment=" + averageSentiment +
                ", positive=" + positive +
                ", negative=" + negative +
                '}';
    }
}
