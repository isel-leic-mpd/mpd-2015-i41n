package pt.isel.mpd.util;

/**
 * Created by mcarvalho on 04-05-2015.
 */
public class Averager {

    private final double count;
    private final double sum;

    public Averager(double count, double sum) {
        this.count = count;
        this.sum = sum;
    }

    public final Averager add(Averager a) {

        return new Averager(this.count + a.count, this.sum + a.sum);
    }

    public double average (){
        return ((double)((int)(sum/count*1000)))/1000;
    }

}