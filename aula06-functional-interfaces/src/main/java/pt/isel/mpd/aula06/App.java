/*
 * Copyright (C) Error: on line 4, column 33 in Templates/Licenses/license-default.txt
 The string doesn't match the expected date/time format. The string to parse was: "16/mar/2015". The expected format was: "MMM d, yyyy". Miguel Gamboa at CCISEL
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
package pt.isel.mpd.aula06;

/**
 *
 * @author Miguel Gamboa at CCISEL
 */

class WeatherInfo{int tempC;}

public class App {
    public static <T> T fetch(Supplier<T> s){ return s.get(); }
    public static String fetchString(Supplier<String> s){ return s.get(); }
    public static void execute(Runnable r){ r.run(); }
    public static Supplier<Integer> getter() { return () -> {return 5;}; }
    //public static Supplier<Integer> getter() { return () -> 5; }

    public static void main(String [] args){
        System.out.println(fetchString(() -> {return "Ola";})); // Passagem de Lambda por parametro
        execute(() -> {}); // Passagem de Lambda por parametro
        Predicate<WeatherInfo> evalTempC = (WeatherInfo w) -> w.tempC > 18; // afectacao de Lambda a variavel
        // assert getter().get().equals(5) : "Getter not evaluating to 5 number"; // Lambda como retorno do metodo
        
        if(getter().equals(5) == false) throw new AssertionError();
    }  
}
