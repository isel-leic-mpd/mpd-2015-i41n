package pt.isel.mpd.util;

import java.util.Comparator;
import java.util.function.Function;

/**
 * Created by mcarvalho on 30-04-2015.
 */
class ReversableCmp<T, R extends Comparable<R>> implements Comparator<T> {

    private final Function<T, R> sup;

    public ReversableCmp(Function<T, R> sup) {
        this.sup = sup;
    }

    @Override
    public int compare(T o1, T o2) {
        return sup.apply(o1).compareTo(sup.apply(o2));
    }

    @Override
    public Comparator<T> reversed() {
        return (o1, o2) -> compare(o2, o1);
    }

    public <R2 extends Comparable<R2>> ReversableCmp<T, R2> andThen(Function<T, R2> sup2) {
        ReversableCmp cmp2 = new ReversableCmp(sup2);
        return new ReversableCmp<T, R2>(sup2){
            @Override
            public int compare(T o1, T o2) {
                int res = ReversableCmp.this.compare(o1, o2);
                return res != 0? res : cmp2.compare(o1, o2);
            }
        };
    }
}
