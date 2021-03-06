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
package pt.isel.mpd.monitrgw.app;

import pt.isel.mpd.monitrgw.model.IMonitrMarketData;
import pt.isel.mpd.monitrgw.service1.MonitrServiceEager;
import pt.isel.mpd.monitrgw.service2.MonitrServiceLazy;
import pt.isel.mpd.monitrgw.webapi.MonitrApi;

import java.util.function.Supplier;


public class Program {

    public static void main(String[] args) throws InterruptedException {

        MonitrApi.GetStockDetails("AAPL");

        duration("Get last news: ", () -> System.out.println(MonitrApi.GetLastNews()));
        duration("Get stock: ", () -> System.out.println(MonitrApi.GetStockDetails("AAPL")));
        duration("Get analysis: ", () -> System.out.println(MonitrApi.GetStockAnalysis("VZ")));

        /**
         * IMonitrMarketData ---> IMonitrStockDetails --> IMonitrStockAnalysisData
         */
        IMonitrMarketData news1 = duration("Get news Eager: ", () -> MonitrServiceEager
                .GetLastNews() // 1 http request GetLasNews
                .findFirst()
                .get()); // 1 http request StockDetails + 1 hhtp reqiuesr Analysis

        IMonitrMarketData news2 = MonitrServiceLazy
                .GetLastNews() // 1 http request GetLasNews
                .findFirst()
                .get(); // 0 requests

        news2.getStockDetails(); // 1 request
        news2.getStockDetails(); // 0 request + ja foi CACHED

    }


    public static void duration(String label, Runnable action){
        duration(label, () -> { action.run(); return null; });
    }

    public static <T > T duration(String label, Supplier<T> action) {
        long start = System.nanoTime();
        T res = action.get();
        long duration = (System.nanoTime() - start) / 1_000_000; // micro seconds
        System.out.println(label + duration + "ms");
        return res;
    }
}
