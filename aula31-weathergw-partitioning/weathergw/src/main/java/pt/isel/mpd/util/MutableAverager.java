package pt.isel.mpd.util;

/**
 * Created by mcarvalho on 04-05-2015.
 */
public class MutableAverager {

    private double count =0;
    private double sum =0;

    public void add(Integer x) {
        sum += x;
        count++;
    }

    public double average (){
        return ((double)((int)(sum/count*1000)))/1000;
    }

}